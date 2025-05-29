using projkat.components;
using System;
using System.Drawing;
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
            addAdminPanelButton();
        }

        public void addAdminPanelButton()
        {
            Button adminPanelButton = new Button();
            adminPanelButton.Size = new System.Drawing.Size(161,30);
            adminPanelButton.ForeColor = Color.FromArgb(128, 255, 0);
            adminPanelButton.BackColor = Color.FromArgb(60, 60, 60);
            adminPanelButton.FlatStyle = FlatStyle.Flat;
            adminPanelButton.Text = "admin panel";
            adminPanelButton.Click += adminPanelButton_Click;
            if (User.GetInstance().Role.ToLower().Equals("admin"))
            {
                groupBox1.Controls.Add(adminPanelButton);
                adminPanelButton.Location = new System.Drawing.Point(6, 215);
            }
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
            ServiceGridView serviceGridView = new ServiceGridView();
            serviceGridView.Dock = DockStyle.Fill;
            servicePanel.Controls.Clear();
            servicePanel.Controls.Add(serviceGridView);           
        }

        private void button1_Click(object sender, EventArgs e)
        {
            RequestServiceComponent requestServiceComponent = new RequestServiceComponent();
            requestServiceComponent.Dock = DockStyle.Fill;
            servicePanel.Controls.Clear();
            servicePanel.Controls.Add(requestServiceComponent);
        }

        private void adminPanelButton_Click(object sender, EventArgs e)
        {
            if (User.GetInstance().Role.ToLower().Equals("admin"))
            {
                AdminPanelWrapperComponent adminPanelWrapperComponent = new AdminPanelWrapperComponent();
                adminPanelWrapperComponent.Dock = DockStyle.Fill;
                servicePanel.Controls.Clear();
                servicePanel.Controls.Add(adminPanelWrapperComponent);
            }
        }

        private void groupBox1_Enter(object sender, EventArgs e)
        {

        }
    }
}
