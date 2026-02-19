import React, { useState, useEffect } from 'react';
import DropdownVehicleMenu from './DropdownVehicleMenu';

const GenerationDropdown = ({setGeneration, selectedModel}) => {

    const [isGenerationDropdownClicked, setIsGenerationDropdownClicked] = useState(false);
    const [generationItems, setGenerationItems] = useState([]);
    
    useEffect(() => {
        const  fetchGeneration = async () => {
            try {
                setGenerationItems([]);
                if(isGenerationDropdownClicked && selectedModel != null) {
                    const response = await fetch("http://localhost:8080/vehicle/getGeneration", {
                        method: "POST",
                        headers: {
                          "Content-Type": "application/json"
                        },
                        body: JSON.stringify({model: selectedModel})
                      })
                    const data = await response.json();
            
                    if(response.ok){
                        setGenerationItems(prevItems => [...prevItems, ...data.map(element => element )]);
                    }
                }else{
                    setGenerationItems([]);
                }
                
            } catch (error) {
                console.error("Error fetching makes:", data.message || error);
            }
        }
        fetchGeneration();
    }, [isGenerationDropdownClicked,selectedModel])
    
    return (
        <>
            <DropdownVehicleMenu setValue={setGeneration}
                                 dropdownButtonText={"select generation"}
                                 dropdownItems={generationItems}
                                 setIsDropdownClicked={setIsGenerationDropdownClicked} />
        </>
    );
}

export default GenerationDropdown;