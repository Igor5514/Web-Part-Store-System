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

        ComboBox makeComboBox;
        ComboBox modelComboBox;
        ComboBox generationComboBox;
        ListBox engineListBox;
        TextBox engineTextBox;

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


            makeComboBox = new ComboBox();
            makeComboBox.Click += new EventHandler(makeComboBox_Click);
            makeComboBox.Location = new Point(190, 70);
            makeComboBox.Size = new Size(200,30);


            Label modelLabel = new Label();
            modelLabel.Text = "Vehicle Model: ";
            modelLabel.ForeColor = Color.White;
            modelLabel.Location = new Point(80, 115);

            modelComboBox = new ComboBox();
            modelComboBox.Click += new EventHandler(modelComboBox_Click);
            modelComboBox.Location = new Point(190, 110);
            modelComboBox.Size = new Size(200, 30);


            Label generationLabel = new Label();
            generationLabel.Text = "Vehicle Generation: ";
            generationLabel.ForeColor = Color.White;
            generationLabel.Location = new Point(80, 155);


            generationComboBox = new ComboBox();
            generationComboBox.Click += new EventHandler(generationComboBox_Click);
            generationComboBox.Location = new Point(190, 150);
            generationComboBox.Size = new Size(200, 30);

            Label engineLabel = new Label();
            engineLabel.Text = "Vehicle Engine: ";
            engineLabel.ForeColor = Color.White;
            engineLabel.Location = new Point(80, 195);

            engineTextBox = new TextBox();
            engineTextBox.TextChanged += new EventHandler(engineTextBox_Change);
            engineTextBox.Location = new Point(190, 190);
            engineTextBox.Size = new Size(200, 35);
            int locationX = engineTextBox.Location.X;
            int locationY = engineTextBox.Location.Y + 20;
            engineListBox = new ListBox();
            engineListBox.Location = new Point(locationX, locationY);
            engineListBox.Size = new Size(200, 100);

            this.Controls.Add(makeLabel);
            this.Controls.Add(makeComboBox);
            this.Controls.Add(modelLabel);
            this.Controls.Add(modelComboBox);
            this.Controls.Add(generationLabel);
            this.Controls.Add(generationComboBox);
            this.Controls.Add(engineLabel);
            this.Controls.Add(engineTextBox);


        }

        private void makeComboBox_Click(object sender, EventArgs e)
        {
            //getAllVehicleMake<Make>("getMake", makeComboBox, "make");
        }

        private void modelComboBox_Click(object sender, EventArgs e)
        {
            //getAllVehicleMake<ModelObject>("getModelAll", modelComboBox, "model");
            
        }

        private void generationComboBox_Click(object sender, EventArgs e)
        {
            //getAllVehicleMake<Generation>("getGenerationAll", generationComboBox, "generation");

        }

        private void engineTextBox_Change(object sender, EventArgs e)
        {
            if (engineTextBox.Text.Length > 0) {
                this.Controls.Add(engineListBox);
                getAllVehicleMake<Engine>("getEngineAll", engineListBox, "engine");
            }
            else
            {
                engineListBox.Items.Clear();
                this.Controls.Remove(engineListBox);
            }

        }

        public async void getAllVehicleMake<T>(string methodType, ListBox listBox, String propName)
        {
            try
            {
                HttpResponseMessage httpResponseMessage = await HttpClientProvider.Client.GetAsync($"http://localhost:8080/vehicle/{methodType}");
                httpResponseMessage.EnsureSuccessStatusCode();
                String responseData = await httpResponseMessage.Content.ReadAsStringAsync();
                List<T> makeList = JsonSerializer.Deserialize<List<T>>(responseData);
                if (httpResponseMessage.StatusCode == HttpStatusCode.OK)
                {
                    listBox.Items.Clear();
                    for(int i = 0; i < makeList.Count; i++)
                    {
                        string value = typeof(T).GetProperty(propName).GetValue(makeList[i])?.ToString() ?? "";

                        listBox.Items.Add(value);
                    }
                }
                else
                {
                    Console.WriteLine(responseData);
                }

            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
        }

    }
}
