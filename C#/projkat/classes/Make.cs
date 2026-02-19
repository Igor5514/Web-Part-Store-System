using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace projkat.classes
{
    public class Make
    {
        public int make_id { get; set; }
        public string make {  get; set; }

        public Make(int make_id, string make) {
            this.make_id = make_id;
            this.make = make;
        }

    }
}
