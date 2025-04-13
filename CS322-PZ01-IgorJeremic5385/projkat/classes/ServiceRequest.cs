using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace projkat.classes
{
    public class ServiceRequest
    {
        public int ServiceId { get; set; }
        public string FullName { get; set; }
        public string Email { get; set; }
        public string MechEmail { get; set; }
        public string ProblemType { get; set; }
        public string ProblemDescription { get; set; }
        public bool IsDone { get; set; }

        public ServiceRequest() { }

        public ServiceRequest(string fullName, string email, string mechEmail, string problemType, string problemDescription, bool isDone)
        {
            FullName = fullName;
            Email = email;
            MechEmail = mechEmail;
            ProblemType = problemType;
            ProblemDescription = problemDescription;
            IsDone = isDone;
        }
    }
}
