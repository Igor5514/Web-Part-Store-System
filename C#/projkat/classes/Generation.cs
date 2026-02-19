using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace projkat.classes
{
    public class Generation
    {
        public int generation_id { get; set; }
        public string generation { get; set; }

        public Generation(int generation_id, string generation)
        {
            this.generation_id = generation_id;
            this.generation = generation;
        }
    }
}
