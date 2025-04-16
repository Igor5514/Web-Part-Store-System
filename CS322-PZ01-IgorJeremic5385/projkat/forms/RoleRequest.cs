using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace projkat.forms
{
    public class RoleRequest
    {
        public string Role { get; set; }
        public string Email { get; set; }

        public RoleRequest() { }

        public RoleRequest(string role, string email)
        {
            Role = role;
            Email = email;
        }
    }
}
