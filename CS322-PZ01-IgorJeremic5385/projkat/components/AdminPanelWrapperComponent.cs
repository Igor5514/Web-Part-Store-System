using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace projkat.components
{
    public partial class AdminPanelWrapperComponent : Panel
    {
        public AdminPanelWrapperComponent()
        {
            InitializeComponent();
            addMenuStripToAdminPanel();

        }

        public void addMenuStripToAdminPanel()
        {
            adminPanel.Dock = DockStyle.Fill;

            MenuStrip menuStrip = new MenuStrip();

            ToolStripMenuItem roleManagmentToolStripMenuItem = new ToolStripMenuItem("role managment");
            ToolStripMenuItem userManagmentToolStripMenuItem = new ToolStripMenuItem("user managment");
            ToolStripMenuItem vehicleManagmentToolStripMenuItem = new ToolStripMenuItem("vehicle managment");

            roleManagmentToolStripMenuItem.Click += roleManagmentItem_Click;
            userManagmentToolStripMenuItem.Click += userManagmentItem_Click;
            vehicleManagmentToolStripMenuItem.Click += vehicleManagmentItem_Click;

            menuStrip.Items.Add(roleManagmentToolStripMenuItem);
            menuStrip.Items.Add(userManagmentToolStripMenuItem);
            menuStrip.Items.Add(vehicleManagmentToolStripMenuItem);

            this.Controls.Add(menuStrip);
        }

        private void roleManagmentItem_Click(object sender, EventArgs e)
        {
            RoleManagementGridView roleManagementGridView = new RoleManagementGridView();
            roleManagementGridView.Dock = DockStyle.Fill;
            this.Controls.Add(roleManagementGridView);
        }

        private void userManagmentItem_Click(object sender, EventArgs e)
        {

        }

        private void vehicleManagmentItem_Click(object sender, EventArgs e)
        {

        }
    }
}
