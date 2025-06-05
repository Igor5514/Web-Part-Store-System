using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace projkat.classes
{
    public class VehicleComponent
    {
        public String vehicleComponentType { get; set; }
        public String vehicleComponentValue { get; set; }

        public VehicleComponent(String vehicleComponentType, String vehicleComponentValue) {
            this.vehicleComponentType = vehicleComponentType;
            this.vehicleComponentValue = vehicleComponentValue;
        }

    }
}
