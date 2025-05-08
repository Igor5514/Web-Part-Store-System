using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace projkat.classes
{
    public class RequestedRoles
    {
        public int roleId { get; set; }
        public string fullName { get; set; }
        public string email { get; set; }
        public string role { get; set; }
        public Boolean isAccepted { get; set; }

        public RequestedRoles() { }

        public RequestedRoles(int roleId, string fullName, string email, string role, bool isAccepted)
        {
            this.roleId = roleId;
            this.fullName = fullName;
            this.email = email;
            this.role = role;
            this.isAccepted = isAccepted;
        }
    }
}

