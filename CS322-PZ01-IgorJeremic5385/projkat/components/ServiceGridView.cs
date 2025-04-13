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

namespace projkat
{
    public partial class ServiceGridView : DataGridView
    {
        public ServiceGridView()
        {
            InitializeComponent();
            InitializeDataGridView();
            LoadAllRequests(User.GetInstance().Email);
        }

        private void InitializeDataGridView()
        {
            this.AllowUserToAddRows = false;
            this.AllowUserToDeleteRows = false;
            this.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            this.RowHeadersVisible = false;
            this.SelectionMode = DataGridViewSelectionMode.FullRowSelect;

            this.Columns.Add("id", "id");
            this.Columns.Add("FullName", "Full Name");
            this.Columns.Add("Email", "Email");
            this.Columns.Add("ProblemType", "Problem Type");
            this.Columns.Add("ProblemDescription", "Problem Description");

            DataGridViewButtonColumn buttonColumn = new DataGridViewButtonColumn
            {
                Name = "IsDone",
                HeaderText = "Is Done",
                Text = "Complete",
                UseColumnTextForButtonValue = true
            };
            this.Columns.Add(buttonColumn);

            this.CellContentClick += new DataGridViewCellEventHandler(ServiceGridView_CellContentClick);
        }

        private void ServiceGridView_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.ColumnIndex == this.Columns["IsDone"].Index)
            {
                MessageBox.Show("Task marked as completed!");
            }
        }

        public async void LoadAllRequests(String email)
        {
            using (HttpClient client = new HttpClient())
            {
                try
                {
                    string jsonString = JsonSerializer.Serialize(email);
                    StringContent content = new StringContent(jsonString, Encoding.UTF8, "application/json");
                    HttpResponseMessage httpResponseMessage = await client.PostAsync("http://localhost:8080/services/getServiceByEmail", content);
                    httpResponseMessage.EnsureSuccessStatusCode();

                  
                    String responseData = await httpResponseMessage.Content.ReadAsStringAsync();
                    List<ServiceRequest> serviceRequestList =  JsonSerializer.Deserialize<List<ServiceRequest>>(responseData, new JsonSerializerOptions { PropertyNameCaseInsensitive = true});
                    for(int i = 0; i < serviceRequestList.Count; i++)
                    {
                        ServiceRequest serviceRequest = serviceRequestList[i];
                        this.Rows.Add(serviceRequest.ServiceId,serviceRequest.FullName, serviceRequest.Email, serviceRequest.ProblemType, serviceRequest.ProblemDescription);              
                    }
                    

                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                }
            }
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }



    }
}
