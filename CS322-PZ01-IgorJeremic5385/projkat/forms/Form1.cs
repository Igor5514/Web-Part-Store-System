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
    public partial class Form1 : Form
    {
        static Panel panelContainer;
        static LoginForm login = new LoginForm(switchPanels);
        static RegistrationForm registrationForm = new RegistrationForm(switchPanels);
        public Form1()
        {
            InitializeComponent();
            initialisePanel();
            showPanel(login);

            
        }

        public void initialisePanel()
        {
            panelContainer = new Panel();
            panelContainer.Dock = DockStyle.Fill;
            panelContainer.BackColor = Color.Blue;

            this.Controls.Add(panelContainer);
        }

        public static void switchPanels(String formType)
        {
            switch (formType)
            {
                case "login":
                    showPanel(login); 
                    break;
                case "registration":
                    showPanel(registrationForm);
                    break;
                case "main_form":        
                    MainForm mainForm = new MainForm(switchPanels);

                    showPanel(mainForm);
                    break;    
            }
        }

        public static void showPanel(Form form)
        { 
            panelContainer.Controls.Clear();
            form.TopLevel = false;
            form.Dock = DockStyle.Fill;
            panelContainer.Controls.Add(form);
            form.Show();
        }


    }
}
