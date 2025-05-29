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
    public partial class FilteredComboBox<T> : GroupBox
    {

        TextBox vehicleTextBox = new TextBox();
        ListBox vehicleListBox = new ListBox();
        public List<T> attributesList;
        int textBoxLoactionX;
        int textBoxLoactionY;
        string method;
        string endpoint;

        public FilteredComboBox(int textBoxLoactionX, int textBoxLoactionY, string method, string endpoint)
        {
            this.textBoxLoactionX = textBoxLoactionX;
            this.textBoxLoactionY = textBoxLoactionY;
            this.method = method;
            this.endpoint = endpoint;
            initializeFilteredComboBox();
        }

        public void initializeFilteredComboBox()
        {
            this.Height = 20;

            vehicleTextBox.TextChanged += new EventHandler(textBox_Change);
            vehicleTextBox.Size = new Size(200, 35);

            this.Location = new Point(textBoxLoactionX, textBoxLoactionY);
            this.Controls.Add(vehicleTextBox);

            vehicleListBox.SelectedIndexChanged += VehicleListBox_SelectedIndexChanged;

        }

   

        private void textBox_Change(object sender, EventArgs e)
        {
            Control parentControl = this.Parent;
            int locationX = this.Location.X;
            int locationY = this.Location.Y + 20;
            vehicleListBox.Size = new Size(200, 100);
            vehicleListBox.Location = new Point(locationX, locationY);
            string vehicleListBoxText = vehicleTextBox.Text;
            if (vehicleListBoxText.Length > 0)
            {
                parentControl.Controls.Add(vehicleListBox);
                vehicleListBox.BringToFront();
                getAllVehicleMake(method, endpoint, vehicleListBoxText);
            }
            else
            {
                vehicleListBox.Items.Clear();
                parentControl.Controls.Remove(vehicleListBox);
            }

        }

        private void VehicleListBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            Control parentControl = this.Parent;

            if (vehicleListBox.SelectedItem != null)
            {
                string selectedItem = vehicleListBox.SelectedItem.ToString();
                parentControl.Controls.Remove(vehicleListBox);
                vehicleTextBox.Text = selectedItem;
            }
        }

        public void populateAttributesList(string propName, string vehicleListBoxText)
        {
            vehicleListBox.Items.Clear();
            for (int i = 0; i < attributesList.Count; i++)
            {
                string value = typeof(T).GetProperty(propName).GetValue(attributesList[i])?.ToString() ?? "";
                if (value.Contains(vehicleListBoxText))
                {
                    vehicleListBox.Items.Add(value);
                }
            }
        }

        public async void getAllVehicleMake(string methodType, String propName, String vehicleListBoxText)
        {
            try
            {
                if (attributesList == null || attributesList.Count == 0) {
                    HttpResponseMessage httpResponseMessage = await HttpClientProvider.Client.GetAsync($"http://localhost:8080/vehicle/{methodType}");
                    httpResponseMessage.EnsureSuccessStatusCode();
                    String responseData = await httpResponseMessage.Content.ReadAsStringAsync();
                    attributesList = JsonSerializer.Deserialize<List<T>>(responseData);
                    
                    if (httpResponseMessage.StatusCode == HttpStatusCode.OK)
                    {
                        populateAttributesList(propName, vehicleListBoxText);
                    }
                    else
                    {
                        Console.WriteLine(responseData);
                    }
                }
                else
                {
                    populateAttributesList(propName, vehicleListBoxText);
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
        }

    }

}
