using projkat.classes;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Windows.Forms;

namespace projkat
{
    public partial class RequestServiceComponent : GroupBox
    {
        TextBox fullNameTextBox;
        ComboBox mechEmailComboBox;
        TextBox problemTypeTextBox;
        TextBox problemDescriptionTextBox;
        Label fullNameErrorLabel;
        Label mechEmailErrorLabel;
        Label problemTypeErrorLabel;
        Label problemDescriptionErrorLabel;
        Label serviceRequestResponseLabel;
        public RequestServiceComponent()
        {
            InitializeComponent();
            InitializeUI();
            loadMechanicEmails();
        }

        public async void loadMechanicEmails()
        {
            try
            {
                HttpResponseMessage httpResponseMessage = await HttpClientProvider.Client.GetAsync("http://localhost:8080/services/getMechEmails");
                httpResponseMessage.EnsureSuccessStatusCode();

                String jsonResponse = await httpResponseMessage.Content.ReadAsStringAsync();
                List<String> mechEmails = JsonSerializer.Deserialize<List<String>>(jsonResponse);
                    
                for(int i = 0; i< mechEmails.Count; i++)
                {
                    mechEmailComboBox.Items.Add(mechEmails[i]);
                }

            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
           
        }

        private void InitializeUI()
        {     
            GroupBox groupBox = new GroupBox
            {
                Text = "Service Request",
                Size = new System.Drawing.Size(400, 255),
                Location = new System.Drawing.Point(10, 10)
            };
            
            this.Controls.Add(groupBox);

            this.Dock = DockStyle.Fill;

            groupBox.Location = new System.Drawing.Point(
                this.ClientSize.Width - 100 / 2 ,
                this.ClientSize.Height - 60 / 2
            );


            Label fullNameLabel = new Label
            {
                Text = "Full Name:",
                ForeColor = System.Drawing.Color.White,
                Location = new System.Drawing.Point(10, 30),
                Size = new System.Drawing.Size(100, 30)
            };
            groupBox.Controls.Add(fullNameLabel);

            fullNameTextBox = new TextBox
            {
                Name = "fullNameTextBox",
                Location = new System.Drawing.Point(130, 30),
                Size = new System.Drawing.Size(250, 20)
            };
            groupBox.Controls.Add(fullNameTextBox);

            fullNameErrorLabel = new Label
            {
                ForeColor = System.Drawing.Color.Red,
                Location = new System.Drawing.Point(130, 50),
                Size = new System.Drawing.Size(120, 20)
            };
            groupBox.Controls.Add(fullNameErrorLabel);

            Label mechEmailLabel = new Label
            {
                Text = "Mech Email:",
                ForeColor = System.Drawing.Color.White,
                Location = new System.Drawing.Point(10, 70),
                Size = new System.Drawing.Size(100, 30)
            };
            groupBox.Controls.Add(mechEmailLabel);

            mechEmailComboBox = new ComboBox
            {
                Name = "mechEmailComboBox",
                Location = new System.Drawing.Point(130, 70),
                Size = new System.Drawing.Size(250, 20)
            };
            groupBox.Controls.Add(mechEmailComboBox);

            mechEmailErrorLabel = new Label
            {
                ForeColor = System.Drawing.Color.Red,
                Location = new System.Drawing.Point(130,90),
                Size = new System.Drawing.Size(120, 20)
            };
            groupBox.Controls.Add(mechEmailErrorLabel);

            Label problemTypeLabel = new Label
            {
                Text = "Problem Type:",
                ForeColor = System.Drawing.Color.White,
                Location = new System.Drawing.Point(10, 110),
                Size = new System.Drawing.Size(100, 30)
            };
            groupBox.Controls.Add(problemTypeLabel);

            problemTypeTextBox = new TextBox
            {
                Name = "problemTypeTextBox",
                Location = new System.Drawing.Point(130, 110),
                Size = new System.Drawing.Size(250, 20)
            };
            groupBox.Controls.Add(problemTypeTextBox);

            problemTypeErrorLabel = new Label
            {
                ForeColor = System.Drawing.Color.Red,
                Location = new System.Drawing.Point(130, 130),
                Size = new System.Drawing.Size(120, 20)
            };
            groupBox.Controls.Add(problemTypeErrorLabel);

            Label problemDescriptionLabel = new Label
            {
                Text = "Problem Description:",
                ForeColor = System.Drawing.Color.White,
                Location = new System.Drawing.Point(10, 150),
                Size = new System.Drawing.Size(120, 30)
            };
            groupBox.Controls.Add(problemDescriptionLabel);

            problemDescriptionTextBox = new TextBox
            {
                Name = "problemDescriptionTextBox",
                Location = new System.Drawing.Point(130, 150),
                Multiline = true,
                Size = new System.Drawing.Size(250, 50)
            };
            groupBox.Controls.Add(problemDescriptionTextBox);

            problemDescriptionErrorLabel = new Label
            {
                ForeColor = System.Drawing.Color.Red,
                Location = new System.Drawing.Point(130, 200),
                Size = new System.Drawing.Size(120, 20)
            };
            groupBox.Controls.Add(problemDescriptionErrorLabel);

            serviceRequestResponseLabel = new Label
            {
                Location = new System.Drawing.Point(150, 220),
                Size = new System.Drawing.Size(120, 25)
            };
            groupBox.Controls.Add(serviceRequestResponseLabel);


            Button submitButton = new Button {
                Name = "submitButton",
                Text = "submit",
                Size = new System.Drawing.Size(70, 25),
                Location = new System.Drawing.Point(310, 220),
                ForeColor = System.Drawing.Color.White,

            };
            submitButton.Click += submitButton_Click;
            groupBox.Controls.Add(submitButton);

            
            
        }
        private void submitButton_Click(object sender, EventArgs e)
        {
            Boolean isFullNameValid = false;
            Boolean isMechEmailValid = false;
            Boolean isProblemTypeValid = false;
            Boolean isProblemDescriptionValid = false;

            string fullName = fullNameTextBox.Text;
            string mechEmail = mechEmailComboBox.Text;
            string problemType = problemTypeTextBox.Text;  
            string problemDescription = problemDescriptionTextBox.Text;

            if (string.IsNullOrEmpty(fullName))
            {
                fullNameErrorLabel.Text = "please enter full name";
            }
            else
            {
                isFullNameValid = true;
            }

            if (string.IsNullOrEmpty(mechEmail))
            {
                mechEmailErrorLabel.Text = "please enter mechanic email";
            }
            else
            {
                isMechEmailValid = true;
            }

            if (string.IsNullOrEmpty(problemType))
            {
                problemTypeErrorLabel.Text = "please emter problem type";
            }
            else
            {
                isProblemTypeValid = true;
            }

            if (string.IsNullOrEmpty(problemDescription))
            {
                problemDescriptionErrorLabel.Text = "please full naem";
            }
            else
            {
                isProblemDescriptionValid = true;
            }

            if (isFullNameValid && isMechEmailValid && isProblemTypeValid && isProblemDescriptionValid) 
            {
                ServiceRequest serviceRequest = new ServiceRequest(fullName, User.GetInstance().Email, mechEmail, problemType, problemDescription, false);
                insertRequestServiceToServer(serviceRequest);

                fullNameTextBox.Text = "";
                mechEmailComboBox.Text = "";
                problemTypeTextBox.Text = "";
                problemDescriptionTextBox.Text = "";
            }

            
        }

        public async void insertRequestServiceToServer(ServiceRequest serviceRequest)
        {
            String responseData = "";
            try
            {
                var options = new JsonSerializerOptions
                {
                    PropertyNamingPolicy = JsonNamingPolicy.CamelCase
                };
                string jsonString = JsonSerializer.Serialize(serviceRequest, options);
                StringContent content = new StringContent(jsonString, Encoding.UTF8, "application/json");
                HttpResponseMessage httpResponseMessage = await HttpClientProvider.Client.PostAsync("http://localhost:8080/services/createService", content);
                httpResponseMessage.EnsureSuccessStatusCode();

                responseData = await httpResponseMessage.Content.ReadAsStringAsync();
                if (httpResponseMessage.StatusCode == HttpStatusCode.OK)
                {
                    serviceRequestResponseLabel.Text = responseData;
                    serviceRequestResponseLabel.ForeColor = Color.Green;
                }
            }
            catch (Exception ex)
            {
                serviceRequestResponseLabel.Text = responseData;
                serviceRequestResponseLabel.ForeColor = Color.Red;
                Console.WriteLine(ex.Message);
            }
            
        }



    }
}