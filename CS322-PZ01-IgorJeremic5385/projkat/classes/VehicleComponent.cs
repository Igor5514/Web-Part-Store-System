using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace projkat.classes
{
    public class VehicleComponent
    {
        public string vehicleComponentMakeValue { get; set; }
        public bool makeValueExist { get; set; }
        public string vehicleComponentModelValue { get; set; }
        public bool modelValueExist { get; set; }
        public string vehicleComponentGenerationValue { get; set; }
        public bool generationValueExist { get; set; }
        public string vehicleComponentEngineValue { get; set; }
        public bool engineValueExist { get; set; }

        public VehicleComponent() { }

        public VehicleComponent(
            string makeValue,
            bool _makeValueExist,
            string modelValue,
            bool _modelValueExist,
            string generationValue,
            bool _generationValueExist,
            string engineValue,
            bool _engineValueExist)
        {
            vehicleComponentMakeValue = makeValue;
            makeValueExist = _makeValueExist;
            vehicleComponentModelValue = modelValue;
            modelValueExist = _modelValueExist;
            vehicleComponentGenerationValue = generationValue;
            generationValueExist = _generationValueExist;
            vehicleComponentEngineValue = engineValue;
            engineValueExist = _engineValueExist;
        }

    }
}
