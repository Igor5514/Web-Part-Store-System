import React from "react";
import "./ShopComponents.css";
import { useVehicle } from "../context/VehicleProvider";

const SecondCard = ({title, img, setPageCountProp, setPartListProp}) => {

    const {vehicle, setVehicle} = useVehicle();

    function buttonListener(e){
        e.preventDefault();
        setPageCountProp(prev => prev += 1)
        if(vehicle.make != ""){
            loadPartsByVehicle();
        }
    }

    const loadPartsByVehicle = async () => {
        console.log(vehicle.model)
        const Vehicle = {
            make: vehicle.make,
            model: vehicle.model,
            generation: vehicle.generation,
            engine: vehicle.engine,
            name: title
        }

        try {
            const response = await fetch("http://localhost:8080/parts/getPartsForVehicle", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(Vehicle)
            });     
            if (response.ok) {
                const data = await response.json();
                setPartListProp(data);
            } else {
                const errorText = await response.text();
                console.error("Server error:", errorText);
            }
        } catch (err) {
            console.error("Network error:", err.message);
        }
    };

    return(
        <div>
            <div className="main-card-container">
                <div className="sub-main-container">
                    <h3 style={{ fontFamily: "'DM Sans', sans-serif" }}>{title}</h3>
                    <img src= {img} />
                </div>
                <button onClick={(e) => {buttonListener(e)}}>Find parts</button>
            </div>
        </div>  
    );
}

export default SecondCard;