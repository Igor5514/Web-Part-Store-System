using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace projkat
{
    public class LoginUser
    {
        public string email { get; set; }
        public string password { get; set; }

        public LoginUser(string _email, string _password)
        {
            email = _email;
            password = _password;
        }

    }
}
