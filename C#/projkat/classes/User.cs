using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace projkat
{
    public class User
    {
        private static User _instance;
        private static readonly object _lock = new object(); 

        public string FullName { get; set; }
        public string Password { get; set; }
        public string Email { get; set; }
        public string PhoneNumber { get; set; }
        public string Role { get; set; }

        public User() { }

        private User(string fullName, string password, string email, string phoneNumber, string role)
        {
            FullName = fullName;
            Password = password;
            Email = email;
            PhoneNumber = phoneNumber;
            Role = role;
        }

        public static User GetInstance(string fullName, string password, string email, string phoneNumber, string role)
        {
            if (_instance == null)
            {
                lock (_lock) 
                {
                    if (_instance == null)
                    {
                        _instance = new User(fullName, password, email, phoneNumber, role);
                    }
                }
            }
            return _instance;
        }

        public static User GetInstance()
        {
            return _instance;
        }

        public static void SetInstance(User user)
        {
            lock (_lock)
            {
                _instance = user;
            }
        }

        public static void ResetInstance()
        {
            lock (_lock)
            {
                _instance = null;
            }
        }
    }
}
