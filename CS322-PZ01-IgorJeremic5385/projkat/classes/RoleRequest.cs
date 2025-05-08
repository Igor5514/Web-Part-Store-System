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
        public Boolean isAccepted { get; set; }

        public RoleRequest() { }

        public RoleRequest(string role, string email, bool isAccepted)
        {
            this.role = role;
            this.email = email;
            this.isAccepted = isAccepted;
        }
    }
}
