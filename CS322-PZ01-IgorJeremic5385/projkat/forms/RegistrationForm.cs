using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net;
using System.Text;
using System.Text.Json;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows.Forms;
using projkat.classes;

namespace projkat
{
    public partial class RegistrationForm : Form
    {

        private Action<String> callback;
        string[] roles = { "admin", "mechanic", "user", "seller" };
        public RegistrationForm(Action<String> _callback)
        {
            this.callback = _callback;
            InitializeComponent();
            this.FormBorderStyle = FormBorderStyle.None;
            this.ControlBox = false;
            for (int i = 0; i < roles.Length; i++)
            {
                roleComboBox.Items.Add(roles[i]);
            }
        }

        public async Task<Boolean> checkIfEmailExists(String email)
        {
            try
            {
                var dict = new Dictionary<string, string>
                {
                    { "email", email }
                };
                string jsonString = JsonSerializer.Serialize(dict);
                StringContent content = new StringContent(jsonString, Encoding.UTF8, "application/json");
                HttpResponseMessage httpResponseMessage = await HttpClientProvider.Client.PostAsync("http://localhost:8080/users/getResByEmail", content);
                httpResponseMessage.EnsureSuccessStatusCode();

                String responseData = await httpResponseMessage.Content.ReadAsStringAsync();
                Boolean response = JsonSerializer.Deserialize<Boolean>(responseData);
                return  response;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                return false;
            }
            
        }

        public async Task<Boolean> registrateUser(RegistrateUser user)
        {
            try
            {
                String serialisedUser = JsonSerializer.Serialize(user);
                StringContent content = new StringContent(serialisedUser, Encoding.UTF8, "application/json");
                HttpResponseMessage httpResponseMessage = await HttpClientProvider.Client.PostAsync("http://localhost:8080/users/add", content);
                httpResponseMessage.EnsureSuccessStatusCode();

                if(httpResponseMessage.StatusCode == HttpStatusCode.OK)
                {
                    return true;
                }
                return false;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                return false;
            }
            
        }

        public async Task<Boolean> validateRegistration()
        {   string fullName = fullNameTextBox.Text;
            string password = passwordTextBox.Text;
            string email = emailTextBox.Text;
            string phone = phoneTextBox.Text;
            string role = roleComboBox.Text;

            string emailPattern = @"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$";
            string passwordPattern = @"^(?=.*[!@#$%^&*()])[a-zA-Z0-9!@#$%^&*()]{6,}$";
            string phoneRegex = @"^\+?\d{1,3}?[-. ]?\(?\d{1,4}?\)?[-. ]?\d{1,4}[-. ]?\d{1,9}$";


            Boolean isEmailValid = false;
            Boolean isPasswordValid = false;
            Boolean isFullNameValid = false;
            Boolean isPhoneValid = false;
            Boolean isRoleValid = false;
            Boolean doesEmailExist = await checkIfEmailExists(email);

            Console.WriteLine(doesEmailExist);

            if (email == "" || !Regex.IsMatch(email, emailPattern))
            {
                emailErrorLabel.Text = "invalid email format";
                isEmailValid = false;
            }
            else if (doesEmailExist)
            {
                emailErrorLabel.Text = "email already exists";
                isEmailValid = false;
            }
            else
            {
                emailErrorLabel.Text = "";
                isEmailValid = true;
            }

            if (password == "" || !Regex.IsMatch(password, passwordPattern))
            {
                isPasswordValid = false;
                passwordErrorLabel.Text = "weak password";
            }
            else
            {
                passwordErrorLabel.Text = "";
                isPasswordValid = true;
            }

            if (fullName == "" )
            {
                isFullNameValid = false;
                fullNameErrorLabel.Text = "invalid full name";
            }
            else
            {
                fullNameErrorLabel.Text = "";
                isFullNameValid = true;
            }

            if (phone == "" || !Regex.IsMatch(phone, phoneRegex))
            {
                isPhoneValid = false;
                phoneErrorLabel.Text = "invalid phone number";
            }
            else
            {
                phoneErrorLabel.Text = "";
                isPhoneValid = true;
            }

            if (role == "" || !roles.Contains(role))
            {
                isRoleValid = false;
                roleErrorLabel.Text = "choose from the combo box";
            }
            else
            {
                roleErrorLabel.Text = "";
                isRoleValid = true;
            }

            if (isEmailValid && isPasswordValid && isFullNameValid && isPhoneValid && isRoleValid)
            {
                RegistrateUser user = new RegistrateUser(fullName, email, password, phone,role);
                User.ResetInstance();
                User newUser = User.GetInstance(fullName, email, password, phone, role);
                return await registrateUser(user);
            }
        
            return false;
        }

        private void loginButton_Click(object sender, EventArgs e)
        {
            callback("login");
        }

        private async void RegistrationButton_Click(object sender, EventArgs e)
        {
            if (await validateRegistration() == true)
            {
                callback("main_form");
            }

                
        }
    }
}
