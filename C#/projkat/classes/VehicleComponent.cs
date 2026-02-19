using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace projkat.classes
{
    public class VehicleComponent
    {
        public string make { get; set; }
        public bool makeValueExist { get; set; }
        public string model { get; set; }
        public bool modelValueExist { get; set; }
        public string generation { get; set; }
        public bool generationValueExist { get; set; }
        public string engine { get; set; }
        public bool engineValueExist { get; set; }

        public VehicleComponent() { }

        public VehicleComponent(
            string make,
            bool makeValueExist,
            string model,
            bool modelValueExist,
            string generation,
            bool generationValueExist,
            string engine,
            bool engineValueExist)
        {
            this.make = make;
            this.makeValueExist = makeValueExist;
            this.model = model;
            this.modelValueExist = modelValueExist;
            this.generation = generation;
            this.generationValueExist = generationValueExist;
            this.engine = engine;
            this.engineValueExist = engineValueExist;
        }

    }
}
