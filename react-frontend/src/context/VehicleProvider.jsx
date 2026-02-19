import React, {createContext, useState, useContext} from 'react';

const VehicleContext = createContext();

export const VehicleProvider = ({ children }) => {
    const [vehicle, setVehicle] = useState({
        make: '',
        model: '',
        generation: '',
        engine: ''
    });

    return(
        <VehicleContext.Provider value={{vehicle, setVehicle}}>
            {children}
        </VehicleContext.Provider>
    );
};

export const useVehicle = () => useContext(VehicleContext);