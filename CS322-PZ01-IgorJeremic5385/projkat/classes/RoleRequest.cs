using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace projkat.forms
{
    public class RoleRequest
    {
        public string role { get; set; }
        public string email { get; set; }
        public Boolean accepted { get; set; }

        public RoleRequest() { }

        public RoleRequest(string role, string email, bool accepted)
        {
            this.role = role;
            this.email = email;
            this.accepted = accepted;
        }
    }
}
