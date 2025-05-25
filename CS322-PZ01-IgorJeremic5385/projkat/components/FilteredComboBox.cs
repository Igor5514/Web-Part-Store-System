using projkat.classes;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Diagnostics;
using System.Linq;
using System.Net.Http;
using System.Net;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Drawing;

namespace projkat.components
{
    public partial class FilteredComboBox : GroupBox
    {

        TextBox vehicleTextBox;
        ListBox vehicleListBox;
        int textBoxLoactionX;
        int textBoxLoactionY;

        public FilteredComboBox(int textBoxLoactionX, int textBoxLoactionY)
        {
            InitializeComponent();
            this.textBoxLoactionX = textBoxLoactionX;
            this.textBoxLoactionY = textBoxLoactionY;
            intialiseFilteredComboBox();
        }

        public void intialiseFilteredComboBox()
        {
            vehicleTextBox = new TextBox();
            vehicleTextBox.TextChanged += new EventHandler(engineTextBox_Change);
            vehicleTextBox.Location = new Point(textBoxLoactionX, textBoxLoactionY);
            vehicleTextBox.Size = new Size(200, 35);
            int locationX = vehicleTextBox.Location.X;
            int locationY = vehicleTextBox.Location.Y + 20;
            vehicleListBox = new ListBox();
            vehicleListBox.Location = new Point(locationX, locationY);
            vehicleListBox.Size = new Size(200, 100);

            this.Controls.Add(vehicleTextBox);
        }

        private void engineTextBox_Change(object sender, EventArgs e)
        {
            string vehicleListBoxText = vehicleListBox.Text;
            if (vehicleListBoxText.Length > 0)
            {
                this.Controls.Add(vehicleListBox);
                getAllVehicleMake<Engine>("getEngineAll", vehicleListBox, "engine", vehicleListBoxText);
            }
            else
            {
                vehicleListBox.Items.Clear();
                this.Controls.Remove(vehicleListBox);
            }

        }

        public async void getAllVehicleMake<T>(string methodType, ListBox listBox, String propName, String vehicleListBoxText)
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
                    for (int i = 0; i < makeList.Count; i++)
                    {
                        string value = typeof(T).GetProperty(propName).GetValue(makeList[i])?.ToString() ?? "";
                        if (value.Contains(vehicleListBoxText))
                        {
                            listBox.Items.Add(value);
                        }
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
