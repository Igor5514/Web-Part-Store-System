using System;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows.Forms;


namespace projkat
{
    public partial class LoginForm : Form
    {
        private Action<String> callback;
        public LoginForm(Action<String> _callback)
        {
            callback = _callback;
            InitializeComponent();
            this.FormBorderStyle = FormBorderStyle.None; 
            this.ControlBox = false;
        }

        public async Task<Boolean> connectToServerForLogin(String jsonString)
        {
            Console.WriteLine("connteting to server");
            using (HttpClient client = new HttpClient())
            {
                try
                {
                    StringContent content = new StringContent(jsonString, Encoding.UTF8, "application/json");
                    HttpResponseMessage httpResponseMessage = await client.PostAsync("http://localhost:8080/users/getByEmail", content);
                    httpResponseMessage.EnsureSuccessStatusCode();

                    if(httpResponseMessage.StatusCode != HttpStatusCode.Unauthorized)
                    {
                        String responseData = await httpResponseMessage.Content.ReadAsStringAsync();
                        User user = JsonSerializer.Deserialize<User>(responseData, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });             
                        User.ResetInstance();
                        User.SetInstance(user); 
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                    
                }
                catch (Exception ex) {
                    Console.WriteLine(ex.Message);
                    return false;
                }
            }
        }

        public async Task<Boolean> loginUser(String email, String password)
        {
            string emailPattern = @"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$";
            string passwordPattern = @"^(?=.*[!@#$%^&*()])[a-zA-Z0-9!@#$%^&*()]{6,}$";

            if (email == "" || !Regex.IsMatch(email, emailPattern))
            {
                emailErrorLabel.Text = "invalid email format";
                return false;

            }
            else if (password == "" || !Regex.IsMatch(password, passwordPattern))
            {
                emailErrorLabel.Text = "";
                passwordErrorLabel.Text = "invalid password format";
                return false;
            }
            else
            {
                LoginUser loginUser = new LoginUser(email, password);
               
                string jsonString = JsonSerializer.Serialize(loginUser);

                if (await connectToServerForLogin(jsonString))
                {
                    return true;
                }
            }
            passwordErrorLabel.Text = "incorrent email or password";
            return false;
        }

        private async void loginButton_Click(object sender, EventArgs e)
        {
            string email = emailTextBox.Text;
            string password = passwordTextBox.Text;

            if (await loginUser(email, password) == true)
            {
                
                callback("main_form");

            }
        }

        private void registrationButton_Click(object sender, EventArgs e)
        {
            callback("registration");
        }

    }
}


