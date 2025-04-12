using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace projkat
{
    public partial class ServiceGridView : DataGridView
    {
        public ServiceGridView()
        {
            InitializeComponent();
            InitializeDataGridView();
        }

        private void InitializeDataGridView()
        {
            this.AllowUserToAddRows = false;
            this.AllowUserToDeleteRows = false;
            this.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            this.RowHeadersVisible = false;
            this.SelectionMode = DataGridViewSelectionMode.FullRowSelect;

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

        public void AddRow(string fullName, string email, string problemType, string problemDescription)
        {
            this.Rows.Add(fullName, email, problemType, problemDescription);
        }
    }
}
