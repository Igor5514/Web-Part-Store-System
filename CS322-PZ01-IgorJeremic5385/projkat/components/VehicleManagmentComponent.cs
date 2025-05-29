using projkat.classes;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Diagnostics;
using System.Drawing;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace projkat.components
{
    public partial class VehicleManagmentComponent : GroupBox
    {

        FilteredComboBox<Make> filteredMakeComboBox;
        FilteredComboBox<ModelObject> filteredModelComboBox;
        FilteredComboBox<Generation> filteredGenerationComboBox;
        FilteredComboBox<Engine> filteredEngineComboBox;

        public VehicleManagmentComponent()
        {
            InitializeComponent();
            intialiseVehicleManagmentGUI();
        }
         

        public VehicleManagmentComponent(IContainer container)
        {
            container.Add(this);
            this.Dock = DockStyle.Fill;
            InitializeComponent();
        }

        public void intialiseVehicleManagmentGUI()
        {
            Label makeLabel = new Label();
            makeLabel.Text = "Vehicle Make: ";
            makeLabel.ForeColor = Color.White;
            makeLabel.Location = new Point(80,75);

            filteredMakeComboBox = new FilteredComboBox<Make>(190, 70, "getMake", "make");

            Label modelLabel = new Label();
            modelLabel.Text = "Vehicle Model: ";
            modelLabel.ForeColor = Color.White;
            modelLabel.Location = new Point(80, 115);

            filteredModelComboBox = new FilteredComboBox<ModelObject>(190, 110, "getModelAll", "model");


            Label generationLabel = new Label();
            generationLabel.Text = "Vehicle Generation: ";
            generationLabel.ForeColor = Color.White;
            generationLabel.Location = new Point(80, 155);

            filteredGenerationComboBox = new FilteredComboBox<Generation>(190, 150, "getGenerationAll", "generation");

            Label engineLabel = new Label();
            engineLabel.Text = "Vehicle Engine: ";
            engineLabel.ForeColor = Color.White;
            engineLabel.Location = new Point(80, 195);

            filteredEngineComboBox = new FilteredComboBox<Engine>(190, 190, "getEngineAll", "engine");
           
            this.Controls.Add(makeLabel);
            this.Controls.Add(filteredMakeComboBox);
            this.Controls.Add(modelLabel);
            this.Controls.Add(filteredModelComboBox);
            this.Controls.Add(generationLabel);
            this.Controls.Add(filteredGenerationComboBox);
            this.Controls.Add(engineLabel);
            this.Controls.Add(filteredEngineComboBox);


        }
  

    }
}
