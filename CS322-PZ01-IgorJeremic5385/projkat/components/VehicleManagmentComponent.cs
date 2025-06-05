using projkat.classes;
using System;
using System.ComponentModel;
using System.Drawing;
using System.Windows.Forms;

namespace projkat.components
{
    public partial class VehicleManagmentComponent : GroupBox
    {
        FilteredComboBox<Make> filteredMakeComboBox;
        FilteredComboBox<ModelObject> filteredModelComboBox;
        FilteredComboBox<Generation> filteredGenerationComboBox;
        FilteredComboBox<Engine> filteredEngineComboBox;
        Label makeErrorLabel;
        Label modelErrorLabel;
        Label generationErrorLabel;
        Label engineErrorLabel;

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
            makeErrorLabel = new Label();
            makeLabel.Text = "Vehicle Make: ";
            makeLabel.ForeColor = Color.FromArgb(128, 255, 0);
            makeErrorLabel.ForeColor = Color.Red;
            makeLabel.Location = new Point(80,75);
            makeErrorLabel.Location = new Point(190, 95);

            filteredMakeComboBox = new FilteredComboBox<Make>(190, 70, "getMake", "make");

            Label modelLabel = new Label();
            modelErrorLabel = new Label();
            modelLabel.Text = "Vehicle Model: ";
            modelLabel.ForeColor = Color.FromArgb(128, 255, 0);
            modelErrorLabel.ForeColor = Color.Red;
            modelLabel.Location = new Point(80, 125);
            modelErrorLabel.Location = new Point(190, 135);

            filteredModelComboBox = new FilteredComboBox<ModelObject>(190, 120, "getModelAll", "model");

            Label generationLabel = new Label();
            generationErrorLabel = new Label();
            generationLabel.Text = "Vehicle Generation: ";
            generationLabel.ForeColor = Color.FromArgb(128, 255, 0);
            generationErrorLabel.ForeColor = Color.Red;
            generationLabel.Location = new Point(80, 175);
            generationErrorLabel.Location = new Point(190, 155);

            filteredGenerationComboBox = new FilteredComboBox<Generation>(190, 170, "getGenerationAll", "generation");

            Label engineLabel = new Label();
            engineErrorLabel = new Label();
            engineLabel.Text = "Vehicle Engine: ";
            engineLabel.ForeColor = Color.FromArgb(128, 255, 0);
            engineErrorLabel.ForeColor = Color.Red;
            engineLabel.Location = new Point(80, 225);
            engineErrorLabel.Location = new Point(190, 225);

            filteredEngineComboBox = new FilteredComboBox<Engine>(190, 220, "getEngineAll", "engine");

            Button addNewVehicleButton = new Button();
            addNewVehicleButton.Text = "add new vehicle";
            addNewVehicleButton.ForeColor = Color.FromArgb(128, 255, 0);
            addNewVehicleButton.BackColor = Color.FromArgb(60, 60, 60);
            addNewVehicleButton.Location = new Point(295, 260);
            addNewVehicleButton.Size = new Size(130, 30);
            addNewVehicleButton.FlatStyle = FlatStyle.Flat;
            addNewVehicleButton.Click += new EventHandler(addNewVehicleButton_Click);

            this.Controls.Add(makeLabel);
            this.Controls.Add(filteredMakeComboBox);
            this.Controls.Add(makeErrorLabel);
            this.Controls.Add(modelLabel);
            this.Controls.Add(filteredModelComboBox);
            this.Controls.Add(modelErrorLabel);
            this.Controls.Add(generationLabel);
            this.Controls.Add(filteredGenerationComboBox);
            this.Controls.Add(generationErrorLabel);
            this.Controls.Add(engineLabel);
            this.Controls.Add(filteredEngineComboBox);
            this.Controls.Add(engineErrorLabel);
            this.Controls.Add(addNewVehicleButton);

        }
  
        private async void addNewVehicleButton_Click(object sender, EventArgs e)
        {
            String makeValue = filteredMakeComboBox.getProperty();
            String modelValue = filteredModelComboBox.getProperty();
            String generationValue = filteredGenerationComboBox.getProperty();
            String engineValue = filteredEngineComboBox.getProperty();

            bool isMakeFieldReady = validateFields(makeValue, makeErrorLabel, "Make");
            bool isModelFieldReady = validateFields(modelValue, modelErrorLabel, "Model");
            bool isGenerationFieldReady = validateFields(generationValue, generationErrorLabel, "Generation");
            bool isEngineFieldReady = validateFields(engineValue, engineErrorLabel, "Engine");

            if (isMakeFieldReady && isModelFieldReady && isGenerationFieldReady && isEngineFieldReady) {
                bool makeExist = await filteredMakeComboBox.checkIfPropertyExist("makeExist", makeValue);
                bool modelExist = await filteredModelComboBox.checkIfPropertyExist("modelExist", modelValue);
                bool generationExist = await filteredGenerationComboBox.checkIfPropertyExist("generationExist", generationValue);
                bool engineExist = await filteredEngineComboBox.checkIfPropertyExist("engineExist", engineValue);


            }

        }

        public async void addNewVehicleType(String method, String value)
        {
            
        }

        public bool validateFields(String value, Label errorLabel, String type)
        {
            if (String.IsNullOrWhiteSpace(value))
            {
                errorLabel.Text = $"{type} field can't be empty";
                return false;
            }
            else
            {
                errorLabel.Text = "";
                return true;
            }
        }

    }
}
