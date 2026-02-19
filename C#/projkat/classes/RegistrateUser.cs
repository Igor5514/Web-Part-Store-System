using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace projkat.classes
{
    public class RegistrateUser
    {
        public string fullName { get; set; }
        public string password { get; set; }
        public string email { get; set; }
        public string phoneNumber { get; set; }
        public string role { get; set; }

        public RegistrateUser(string _fullName, string _email, string _password, string _phoneNumber, string _role)
        {
            fullName = _fullName;
            password = _password;
            email = _email;
            phoneNumber = _phoneNumber;
            role = _role;
        }
    }
}
