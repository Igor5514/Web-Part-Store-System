using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace projkat
{
    public partial class MainForm : Form
    {
        private Action<String> callback;

        public MainForm(Action<String> _callback)
        {
            callback = _callback;
            InitializeComponent();
            this.FormBorderStyle = FormBorderStyle.None;
            this.ControlBox = false;
            initialisationMainForm();
        }

        private void logoutButton_Click(object sender, EventArgs e)
        {
            callback("login");
        }

        public void initialisationMainForm()
        {
          
            usernameLabel.Text =User.GetInstance().FullName;
            roleLabel.Text = User.GetInstance().Role;
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (User.GetInstance().Role == "mechanic")
            {
                ServiceGridView serviceGridView = new ServiceGridView();
                serviceGridView.Dock = DockStyle.Fill;
                servicePanel.Controls.Clear();
                servicePanel.Controls.Add(serviceGridView);
            }
                
        }

        private void button1_Click(object sender, EventArgs e)
        {
            RequestServiceComponent requestServiceComponent = new RequestServiceComponent();
            requestServiceComponent.Dock = DockStyle.Fill;
            servicePanel.Controls.Clear();
            servicePanel.Controls.Add(requestServiceComponent);
        }
    }
}
