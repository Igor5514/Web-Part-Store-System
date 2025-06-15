import React, {useState, useRef} from 'react';
import MakeDropdown from './MakeDropdown';
import ModelDropdown from './ModelDropdown';
import GenerationDropdown from './GenerationDropdown';
import EngineDropDown from './EngineDropdown';
import "./ShopComponents.css"
  import { useVehicle } from '../context/VehicleProvider'; 

const CarSelectionMenu = () => {

    const [make, setMake] = useState(null);
    const [model, setModel] = useState(null);
    const [generation, setGeneration] = useState(null);
    const [engine, setEngine] = useState(null);
    const [errorText, setErrorText] = useState("");
    const {vehicle, setVehicle} = useVehicle();

    const handleSearchClick = () => {
        if(make != null && model != null && generation != null && engine != null){
            setErrorText("")
            setVehicle(prev => ({
                ...prev,
                make: make,
                model: model,
                generation: generation,
                engine: engine
            }))
        }else{
            setErrorText("all fields must be filled")
        }
    };

    return (
        <div className='main-car-selection-container'>
            <h5 className='text-white pb-2' style={{backgroundColor:" #1a1a1a"}}>Choose your vehicle:</h5>
            <div className='dropdowns-container'>
                <MakeDropdown setMake={setMake}/>
                <ModelDropdown setModel={setModel} selectedMake={make} />
                <GenerationDropdown setGeneration={setGeneration} selectedModel={model} />
                <EngineDropDown setEngine={setEngine} selectedModel={model} selectedGeneration={generation} />
            </div>
            <div>
                <button className='text-white dropdown-selection-button' onClick={handleSearchClick}>Search parts for your vehicle</button>
                <p style={{color: "red", backgroundColor:" #1a1a1a"}} className='m-0'>{errorText}</p>
            </div>

        </div>
    );
}

export default CarSelectionMenu;