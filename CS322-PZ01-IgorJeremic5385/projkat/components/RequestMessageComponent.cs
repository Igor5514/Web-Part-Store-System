using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace projkat.components
{
    public partial class RequestMessageComponent : Component
    {
        public RequestMessageComponent()
        {
            InitializeComponent();
        }

        public RequestMessageComponent(IContainer container)
        {
            container.Add(this);

            InitializeComponent();
        }
    }
}
