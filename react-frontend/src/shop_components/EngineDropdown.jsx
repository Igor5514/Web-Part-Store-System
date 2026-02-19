import React, { useState, useEffect } from 'react';
import DropdownVehicleMenu from './DropdownVehicleMenu';

const GenerationDropdown = ({setEngine, selectedModel, selectedGeneration}) => {

    const [isEngineDropdownClicked, setIsEngineDropdownClicked] = useState(false);
    const [engineItems, setEngineItems] = useState([]);
    
    useEffect(() => {
        const  fetchEngines = async () => {
            try {
                setEngineItems([]);
                if(isEngineDropdownClicked && selectedModel != null && selectedGeneration != null) {
                    const response = await fetch("http://localhost:8080/vehicle/getEngine", {
                        method: "POST",
                        headers: {
                          "Content-Type": "application/json"
                        },
                        body: JSON.stringify({
                            model: selectedModel,
                            generation: selectedGeneration
                        })
                      })
                    const data = await response.json();
            
                    if(response.ok){
                        setEngineItems(prevItems => [...prevItems, ...data.map(element => element )]);
                    }
                }else{
                    setEngineItems([]);
                }
                
            } catch (error) {
                console.error("Error fetching makes:", data.message || error);
            }
        }
        fetchEngines();
    }, [isEngineDropdownClicked,selectedModel, selectedGeneration])
    
    return (
        <>
            <DropdownVehicleMenu setValue={setEngine}
                                 dropdownButtonText={"select engine"}
                                 dropdownItems={engineItems}
                                 setIsDropdownClicked={setIsEngineDropdownClicked} />
        </>
    );
}

export default GenerationDropdown;