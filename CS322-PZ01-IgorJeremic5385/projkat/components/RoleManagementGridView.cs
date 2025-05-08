using projkat.classes;
using projkat.forms;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Diagnostics;
using System.Linq;
using System.Net.Http;
using System.Runtime.CompilerServices;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace projkat.components
{
    public partial class RoleManagementGridView : DataGridView
    {
        public RoleManagementGridView()
        {
            InitializeComponent();
            InitializeRoleManagementGridView();
            loadRequestedRoles();
        }

        private void InitializeRoleManagementGridView()
        {
            this.AllowUserToAddRows = false;
            this.AllowUserToDeleteRows = false;
            this.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            this.RowHeadersVisible = false;
            this.SelectionMode = DataGridViewSelectionMode.FullRowSelect;

            this.Columns.Add("id", "id");
            this.Columns.Add("FullName", "Full Name");
            this.Columns.Add("Email", "Email");
            this.Columns.Add("RequestedRole", "Requested Role");
     
            DataGridViewButtonColumn acceptButtonColumn = new DataGridViewButtonColumn
            {
                Name = "AcceptRole",
                HeaderText = "Accept Role",
                UseColumnTextForButtonValue = false
            };
            this.Columns.Add(acceptButtonColumn);
            DataGridViewButtonColumn deleteButtonColumn = new DataGridViewButtonColumn
            {
                Text = "Delete",
                Name = "DeleteRequest",
                HeaderText = "Delete Request",
                UseColumnTextForButtonValue = true
            };
            this.Columns.Add(deleteButtonColumn);

            this.CellContentClick += new DataGridViewCellEventHandler(ServiceGridView_CellContentClick);
        }

        private void ServiceGridView_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            
            if (e.ColumnIndex == this.Columns["AcceptRole"].Index)
            {
                String email = this.Rows[e.RowIndex].Cells[2].Value?.ToString();
                String role = this.Rows[e.RowIndex].Cells[3].Value?.ToString();
                Boolean isAccepted = this.Rows[e.RowIndex].Cells[4].Value?.ToString() == "Accepted";
                
                RoleRequest roleRequest = new RoleRequest(role, email, isAccepted);
               
                updateRoleStatus(roleRequest);
                if (this.Rows[e.RowIndex].Cells[e.ColumnIndex].ToString() == "Accept")
                {
                    this.Rows[e.RowIndex].Cells[e.ColumnIndex].Value = "Accepted";
                }
                else
                {
                    this.Rows[e.RowIndex].Cells[e.ColumnIndex].Value = "Accept";
                }
            }

            if (e.ColumnIndex == this.Columns["DeleteRequest"].Index)
            {
                
            }
        }

        public async void deleteRoleRequest()

        {
            try
            {

            }
            catch (Exception ex) { 
                Console.WriteLine(ex.Message);
            }
        }

        public async void updateRoleStatus(RoleRequest roleRequest)
        {
            try
            {
                string jsonString = JsonSerializer.Serialize(roleRequest);
                StringContent content = new StringContent(jsonString, Encoding.UTF8, "application/json");
                HttpResponseMessage httpResponseMessage = await HttpClientProvider.Client.PostAsync("http://localhost:8080/users/updateRole", content);
                httpResponseMessage.EnsureSuccessStatusCode();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
            
        }

        public async void loadRequestedRoles()
        {
            try
            {
                HttpResponseMessage httpResponseMessage = await HttpClientProvider.Client.GetAsync("http://localhost:8080/users/getRoleRequests");
                httpResponseMessage.EnsureSuccessStatusCode();

                String jsonResponse = await httpResponseMessage.Content.ReadAsStringAsync();
                List<RequestedRoles> requestedRolesList = JsonSerializer.Deserialize<List<RequestedRoles>>(jsonResponse);

                for (int i = 0; i < requestedRolesList.Count; i++)
                {
                    RequestedRoles requestedRole = requestedRolesList[i];
                    Console.WriteLine("aaa: "+requestedRole.isAccepted);
                    this.Rows.Add(requestedRole.roleId, requestedRole.fullName, requestedRole.email, requestedRole.role, requestedRole.isAccepted ? "Accepted" : "Accept");
                }

            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
            
        }



    }
}















