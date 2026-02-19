import React, { useState } from "react";
import "../components/Components.css";
import MakeDropdown from "../shop_components/MakeDropdown";
import ModelDropdown from "../shop_components/ModelDropdown";
import GenerationDropdown from "../shop_components/GenerationDropdown";
import EngineDropDown from "../shop_components/EngineDropdown";


const PartForm = () => {
    const [formData, setFormData] = useState({
        name: "",
        partNumber: "",
        brand: "",
        price: "",
        stockQuantity: 0,
        image: null,
        description: "",
        weight: "",
        dimensions: "",
        vehicleType: "",
    });

    const [make, setMake] = useState(null);
    const [model, setModel] = useState(null);
    const [generation, setGeneration] = useState(null);
    const [engine, setEngine] = useState(null);
    const [errorText, setErrorText] = useState("");
  
   

  const handleChange = (e) => {
    const { name, value, type, files } = e.target;
    if (type === "file") {
      setFormData({ ...formData, [name]: files[0] });
    } else {
      setFormData({ ...formData, [name]: value });
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(formData);
  };

  return (
    <form onSubmit={handleSubmit} style={{fontFamily: "'Dual Sans', sans-serif"}} className="p-4 border rounded shadow-sm text-white part-form-container">
      <div className="row input-form-container" style={{backgroundColor: " #1a1a1a"}}  > 
        <div className="col-3" style={{backgroundColor: " #1a1a1a"}}>
            <div className="dropdown-container-wrapper">
                <div className='dropdowns-container'>
                    <MakeDropdown setMake={setMake}/>
                    <ModelDropdown setModel={setModel} selectedMake={make} />
                    <GenerationDropdown setGeneration={setGeneration} selectedModel={model} />
                    <EngineDropDown setEngine={setEngine} selectedModel={model} selectedGeneration={generation} />
                </div>
            </div>
            
        </div>

        <div className="col-4 component-bg">
          <div className="mb-3 component-bg">
            <label className="form-label component-bg">Name:</label>
            <input type="text" className="form-control bg-secondary text-light border-0" name="name" value={formData.name} onChange={handleChange} required />
          </div>

          <div className="mb-3 component-bg">
            <label className="form-label component-bg">Part Number:</label>
            <input type="text" className="form-control bg-secondary text-light border-0" name="partNumber" value={formData.partNumber} onChange={handleChange} required />
          </div>

          <div className="mb-3 component-bg">
            <label className="form-label component-bg">Brand:</label>
            <input type="text" className="form-control bg-secondary text-light border-0" name="brand" value={formData.brand} onChange={handleChange} />
          </div>

          <div className="mb-3 component-bg">
            <label className="form-label component-bg">Price:</label>
            <input type="number" step="0.01" className="form-control bg-secondary text-light border-0" name="price" value={formData.price} onChange={handleChange} />
          </div>

          <div className="mb-3 component-bg">
            <label className="form-label component-bg">Stock Quantity:</label>
            <input type="number" className="form-control bg-secondary text-light border-0" name="stockQuantity" value={formData.stockQuantity} onChange={handleChange} />
          </div>
        </div>

        <div className="col-4 component-bg">
          <div className="mb-3 component-bg">
            <label className="form-label component-bg">Image:</label>
            <input type="file" className="form-control bg-secondary text-light border-0" name="image" accept="image/*" onChange={handleChange} />
          </div>

          <div className="mb-3 component-bg">
            <label className="form-label component-bg">Description:</label>
            <textarea className="form-control bg-secondary text-light border-0" name="description" value={formData.description} onChange={handleChange} />
          </div>

          <div className="mb-3 component-bg">
            <label className="form-label component-bg">Weight (kg):</label>
            <input type="number" step="0.01" className="form-control bg-secondary text-light border-0" name="weight" value={formData.weight} onChange={handleChange} />
          </div>

          <div className="mb-3 component-bg">
            <label className="form-label component-bg">Dimensions:</label>
            <input type="text" className="form-control bg-secondary text-light border-0" name="dimensions" value={formData.dimensions} onChange={handleChange} />
          </div>

          <div className="mb-3 component-bg">
            <label className="form-label component-bg">Vehicle Type:</label>
            <input type="text" className="form-control bg-secondary text-light border-0" name="vehicleType" value={formData.vehicleType} onChange={handleChange} />
          </div>
        </div>
      </div>

      <div className="text-center mt-4 component-bg">
        <button type="submit"style={{border: "1px solid white", padding: "0.5em 2em"}} >Save Part</button>
      </div>
    </form>
  );
};

export default PartForm;
