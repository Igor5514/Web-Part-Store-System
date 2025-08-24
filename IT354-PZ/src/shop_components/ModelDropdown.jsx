import { useState, useEffect } from 'react';
import DropdownVehicleMenu from './DropdownVehicleMenu';

const MakeDropdown = ({setModel, selectedMake}) => {

    const [isModelDropdownClicked, setIsModelDropdownClicked] = useState(false);
    const [modelItems, setModelItems] = useState([]);
    
    useEffect(() => {
        const  fetchModels = async () => {
            try {
                setModelItems([]);
                if(isModelDropdownClicked && selectedMake != null) {
                    const response = await fetch("http://localhost:8080/vehicle/getModel", {
                        method: "POST",
                        headers: {
                          "Content-Type": "application/json"
                        },
                        body: JSON.stringify({make: selectedMake})
                      })
                      
                    const data = await response.json();
            
                    if(response.ok){
                        setModelItems(prevItems => [...prevItems, ...data.map(element => element )]);
                    }
                }else{
                    setModelItems([]);
                }
                
            } catch (error) {
                console.error("Error fetching makes:", data.message || error);
            }
        }
        fetchModels();
    }, [isModelDropdownClicked,selectedMake])
    
    return (
        <>
            <DropdownVehicleMenu setValue={setModel}
                                 dropdownButtonText={"select model"}
                                 dropdownItems={modelItems}
                                 setIsDropdownClicked={setIsModelDropdownClicked} />
        </>
    );
}

export default MakeDropdown;