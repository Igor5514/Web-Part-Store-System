import React, { useState, useEffect } from 'react';
import DropdownVehicleMenu from './DropdownVehicleMenu';

const MakeDropdown = ({setMake}) => {

    const [isMakeDropdownClicked, setIsMakeDropdownClicked] = useState(false);
    const [makeItems, setMakeItems] = useState([]);
    
    
    useEffect(() => {
        const  fetchMakes = async () => {
            try {
                setMakeItems([]);
                if(isMakeDropdownClicked) {
                    const response = await fetch("http://localhost:8080/vehicle/getMake");
                    const data = await response.json();
                    if(response.ok){
                        setMakeItems(prevItems => [...prevItems, ...data.map(element => element.make )]);
                    }
                }else{
                   setMakeItems([]);
                }
                
            } catch (error) {
                console.error("Error fetching makes:", error);
            }
        }
        fetchMakes();
    }, [isMakeDropdownClicked])
    
    return (
        <>
            <DropdownVehicleMenu setValue={setMake}
                                 dropdownButtonText={"select make"} 
                                 dropdownItems={makeItems} 
                                 setIsDropdownClicked={setIsMakeDropdownClicked} />
        </>
    );
}

export default MakeDropdown;