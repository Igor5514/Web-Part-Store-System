using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace projkat.classes
{
    public class Engine
    {
        public int engine_id { get; set; }
        public string engine { get; set; }

        public Engine(int engine_id, string engine)
        {
            this.engine_id = engine_id;
            this.engine = engine;
        }
    }
}
