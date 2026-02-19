using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Diagnostics;
using System.Linq;
using System.Net.Http;
using System.Net;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using System.Windows.Forms;
using projkat.classes;
using System.Windows.Forms.VisualStyles;
using System.Drawing;

namespace projkat
{
    public partial class ServiceGridView : DataGridView
    {
        public ServiceGridView()
        {
            InitializeComponent();
            InitializeDataGridView();
            LoadAllRequests(User.GetInstance().Email, User.GetInstance().Role);
        }

        private void InitializeDataGridView()
        {
            this.AllowUserToAddRows = false;
            this.AllowUserToDeleteRows = false;
            this.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            this.RowHeadersVisible = false;
            this.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            this.DefaultCellStyle.BackColor = Color.FromArgb(40, 40, 40);
            this.ColumnHeadersDefaultCellStyle.ForeColor = Color.FromArgb(128, 255, 0);
            this.ColumnHeadersDefaultCellStyle.BackColor = Color.FromArgb(30, 30, 30);
            this.EnableHeadersVisualStyles = false;
            this.BackgroundColor = Color.FromArgb(40, 40, 40);
            this.ForeColor = Color.White ;
            

            this.Columns.Add("id", "id");
            this.Columns.Add("FullName", "Full Name");
            this.Columns.Add("Email", "Email");
            this.Columns.Add("ProblemType", "Problem Type");
            this.Columns.Add("ProblemDescription", "Problem Description");

            if (User.GetInstance().Role.ToLower().Equals("mechanic"))
            {
                DataGridViewButtonColumn buttonColumn = new DataGridViewButtonColumn
                {
                    Name = "IsDone",
                    HeaderText = "Is Done",
                    UseColumnTextForButtonValue = false
                };
                this.Columns.Add(buttonColumn);
            }
            else
            {
                this.Columns.Add("isDone", "Is Completed");
            }

            this.CellContentClick += new DataGridViewCellEventHandler(ServiceGridView_CellContentClick);
        }

        private void ServiceGridView_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.ColumnIndex == this.Columns["ProblemDescription"].Index) {
                string cellText = this.Rows[e.RowIndex].Cells[e.ColumnIndex].Value?.ToString();
                MessageBox.Show(cellText);
            }

            if (User.GetInstance().Role.ToLower().Equals("mechanic"))
            {
                if (e.ColumnIndex == this.Columns["IsDone"].Index)
                {
                    int serviceId;
                    if (int.TryParse(this.Rows[e.RowIndex].Cells[0].Value?.ToString(), out serviceId))
                    {
                        updateServiceStatus(serviceId);
                        if (this.Rows[e.RowIndex].Cells[e.ColumnIndex].Value == "Complete")
                        {
                            this.Rows[e.RowIndex].Cells[e.ColumnIndex].Value = "Completed";
                        }
                        else
                        {
                            this.Rows[e.RowIndex].Cells[e.ColumnIndex].Value = "Complete";
                        }

                    };
                }
            }
            
        }

        public async void updateServiceStatus(int serviceId)
        {
            try
            {
                string jsonString = JsonSerializer.Serialize(serviceId);
                StringContent content = new StringContent(jsonString, Encoding.UTF8, "application/json");
                HttpResponseMessage httpResponseMessage = await HttpClientProvider.Client.PostAsync("http://localhost:8080/services/updateServiceStatus", content);
                httpResponseMessage.EnsureSuccessStatusCode();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        public async void LoadAllRequests(String email, String role)
        {
            try
            {

                string jsonString = JsonSerializer.Serialize(email.Trim()+ " " + role.Trim());
                StringContent content = new StringContent(jsonString, Encoding.UTF8, "application/json");
                HttpResponseMessage httpResponseMessage = await HttpClientProvider.Client.PostAsync("http://localhost:8080/services/getServiceByEmail", content);
                httpResponseMessage.EnsureSuccessStatusCode();


                String responseData = await httpResponseMessage.Content.ReadAsStringAsync();
                List<ServiceRequest> serviceRequestList = JsonSerializer.Deserialize<List<ServiceRequest>>(responseData, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
                for (int i = 0; i < serviceRequestList.Count; i++)
                {
                    ServiceRequest serviceRequest = serviceRequestList[i];
                    if(User.GetInstance().Role.ToLower().Equals("mechanic")){
                        this.Rows.Add(serviceRequest.ServiceId, serviceRequest.FullName, serviceRequest.Email, serviceRequest.ProblemType, serviceRequest.ProblemDescription, serviceRequest.IsDone ? "Completed" : "Complete");
                    }
                    else
                    {
                        this.Rows.Add(serviceRequest.ServiceId, serviceRequest.FullName, serviceRequest.Email, serviceRequest.ProblemType, serviceRequest.ProblemDescription, serviceRequest.IsDone ? "Completed" : "Not Completed");

                    }
                }


            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
            
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }
    }
}
