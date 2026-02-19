using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace projkat.classes
{
    public class ModelObject
    {
        public int model_id {  get; set; }
        public string model { get; set; }

        public ModelObject(int model_id, string model) { 
            this.model_id = model_id;
            this.model = model;
        }

    }
}
