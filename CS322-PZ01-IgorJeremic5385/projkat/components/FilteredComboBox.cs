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
            this.Height = 25;
            this.Width = 240;

            vehicleTextBox.TextChanged += new EventHandler(textBox_Change);
            vehicleTextBox.Size = new Size(240, 25);
            vehicleTextBox.ForeColor = Color.White;
            vehicleTextBox.BackColor = Color.FromArgb(102, 102, 102);
            vehicleTextBox.BorderStyle = BorderStyle.FixedSingle;
            vehicleTextBox.Font = new Font("Microsoft Sans Serif", 10);

            this.Location = new Point(textBoxLoactionX, textBoxLoactionY);
            this.Controls.Add(vehicleTextBox);

            vehicleListBox.SelectedIndexChanged += VehicleListBox_SelectedIndexChanged;

        }

   

        private void textBox_Change(object sender, EventArgs e)
        {
            Control parentControl = this.Parent;
            int locationX = this.Location.X;
            int locationY = this.Location.Y + 23;
            vehicleListBox.Size = new Size(240, 100);
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
            Control parentControl = this.Parent;

            vehicleListBox.Items.Clear();
            int itemCounter = 0;
            for (int i = 0; i < attributesList.Count; i++)
            {
                string value = typeof(T).GetProperty(propName).GetValue(attributesList[i])?.ToString() ?? "";
                if (value.ToLower().Contains(vehicleListBoxText.ToLower()) && itemCounter<200)
                {
                    vehicleListBox.Items.Add(value);
                    itemCounter++;
                }
               
            }
            if (itemCounter == 0)
            {
                parentControl.Controls.Remove(vehicleListBox);

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

        public async Task<bool> checkIfPropertyExist(string propType, string method, string prop)
        {
            try
            {
                String jsonString = JsonSerializer.Serialize(prop);
                StringContent content = new StringContent(jsonString, Encoding.UTF8, "application/json");
                HttpResponseMessage httpResponseMessage = await HttpClientProvider.Client.PostAsync($"http://localhost:8080/vehicle/{method}", content);
                httpResponseMessage.EnsureSuccessStatusCode();
                String responseData = await httpResponseMessage.Content.ReadAsStringAsync();
                Boolean response = JsonSerializer.Deserialize<Boolean>(responseData);
                return response;
            }
            catch (Exception ex) { 
                Console.WriteLine(ex.Message);
                return false;
            }
            
        }

    }

}
