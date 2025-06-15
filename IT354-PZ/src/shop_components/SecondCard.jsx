import React from "react";
import "./ShopComponents.css";
import { useVehicle } from "../context/VehicleProvider";

const SecondCard = ({title, img, setPageCountProp}) => {

    const {vehicle, setVehicle} = useVehicle();

    function buttonListener(e){
        e.preventDefault();
        setPageCountProp(prev => prev += 1)
        
        console.log(vehicle.make)
        console.log(vehicle.model)
        console.log(vehicle.generation)
    }

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