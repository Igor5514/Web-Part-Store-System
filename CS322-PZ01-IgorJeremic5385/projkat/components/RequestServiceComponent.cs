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
    public partial class RequestServiceComponent : GroupBox
    {
        public RequestServiceComponent()
        {
            InitializeComponent();
            InitializeUI();
        }

        private void InitializeUI()
        {
            GroupBox groupBox = new GroupBox
            {
                Text = "Service Request",
                Size = new System.Drawing.Size(400, 300),
                Location = new System.Drawing.Point(10, 10)
            };
            this.Controls.Add(groupBox);

            Label fullNameLabel = new Label
            {
                Text = "Full Name:",
                ForeColor = System.Drawing.Color.White,
                Location = new System.Drawing.Point(10, 30),
                Size = new System.Drawing.Size(100, 20)
            };
            groupBox.Controls.Add(fullNameLabel);

            TextBox fullNameTextBox = new TextBox
            {
                Name = "fullNameTextBox",
                Location = new System.Drawing.Point(130, 30),
                Size = new System.Drawing.Size(250, 20)
            };
            groupBox.Controls.Add(fullNameTextBox);

            Label emailLabel = new Label
            {
                Text = "Email:",
                ForeColor = System.Drawing.Color.White,
                Location = new System.Drawing.Point(10, 70),
                Size = new System.Drawing.Size(100, 20)
            };
            groupBox.Controls.Add(emailLabel);

            TextBox emailTextBox = new TextBox
            {
                Name = "emailTextBox",
                Location = new System.Drawing.Point(130, 70),
                Size = new System.Drawing.Size(250, 20)
            };
            groupBox.Controls.Add(emailTextBox);

            Label problemTypeLabel = new Label
            {
                Text = "Problem Type:",
                ForeColor = System.Drawing.Color.White,
                Location = new System.Drawing.Point(10, 110),
                Size = new System.Drawing.Size(100, 20)
            };
            groupBox.Controls.Add(problemTypeLabel);

            TextBox problemTypeTextBox = new TextBox
            {
                Name = "problemTypeTextBox",
                Location = new System.Drawing.Point(130, 110),
                Size = new System.Drawing.Size(250, 20)
            };
            groupBox.Controls.Add(problemTypeTextBox);

            Label problemDescriptionLabel = new Label
            {
                Text = "Problem Description:",
                ForeColor = System.Drawing.Color.White,
                Location = new System.Drawing.Point(10, 150),
                Size = new System.Drawing.Size(120, 20)
            };
            groupBox.Controls.Add(problemDescriptionLabel);

            TextBox problemDescriptionTextBox = new TextBox
            {
                Name = "problemDescriptionTextBox",
                Location = new System.Drawing.Point(130, 150),
                Multiline = true,
                Size = new System.Drawing.Size(250, 50)
            };
            groupBox.Controls.Add(problemDescriptionTextBox);

            Label isDoneLabel = new Label
            {
                Text = "Is Done:",
                ForeColor = System.Drawing.Color.White,
                Location = new System.Drawing.Point(10, 210),
                Size = new System.Drawing.Size(100, 20)
            };
            groupBox.Controls.Add(isDoneLabel);

            TextBox isDoneTextBox = new TextBox
            {
                Name = "isDoneTextBox",
                Location = new System.Drawing.Point(130, 210),
                Size = new System.Drawing.Size(250, 20)
            };
            groupBox.Controls.Add(isDoneTextBox);
            Button submitButton = new Button {
                Name = "submitButton",
                Text = "submit",
                Size = new System.Drawing.Size(70, 25),
                Location = new System.Drawing.Point(150, 250),
                ForeColor = System.Drawing.Color.White,

            };
            groupBox.Controls.Add(submitButton);

            
            
        }
        private void submitButton_Click(object sender, EventArgs e)
        {

        }
    }
}