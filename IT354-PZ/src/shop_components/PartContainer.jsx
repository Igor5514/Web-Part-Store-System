import { useState, useEffect, useMemo } from 'react';
import "./ShopComponents.css";
import PartBox from "./PartBox.jsx"

const PartContainer = ({parts = [],setCartItemsProp}) => {
    
    const [isChecked, setIsChecked] = useState({
        avaliable: false,
        notAvaliable: false,
    });

    const handleChange = (event) => {
        const { name, checked } = event.target;
        setIsChecked((prev) => ({
            ...prev, [name]: checked
        }))
    };

    const uniqueBrands = useMemo(() => {
        return [...new Set(parts.map(p => p.brand))];
    }, [parts]);

    useEffect(() => {
        setIsChecked(prevState => {
            let updated = false;
            const newState = { ...prevState };

            uniqueBrands.forEach(brand => {
                
                if (!(brand in newState)) {
                    newState[brand] = false;
                    updated = true;
                }
            });

            return updated ? newState : prevState;
        });
    }, [uniqueBrands]);

    const finalPartList = useMemo(() => {
        const anyFilterActive = Object.entries(isChecked).some(([key, value]) => value === true);
        if(!anyFilterActive) return parts;

        return parts.filter(part => {
            if(isChecked.avaliable && (part.stockQuantity > 0) !== true) return false;
            if(isChecked.notAvaliable && (part.stockQuantity === 0) !== true) return false;

            const brandFilters = Object.entries(isChecked)
                .filter(([key,value]) => key !== 'avaliable' && key !== 'notAvaliable' && value === true)
                .map(([key])=> key);

            if(brandFilters.length > 0 && !brandFilters.includes(part.brand)) return false;

            return true;
        });
    }, [isChecked, parts]);

    return (
        <div className='parts-component' style={{ fontFamily: "'DM Sans', sans-serif", marginTop: "3em"} }>
            <div className='chekbox-containers text-white'>
                <div className='checkbox-design-container'>
                    <h4>Choose avaliability</h4>
                    <div className='avaliable-container'>
                        <label>
                            <input type="checkbox" name='avaliable' checked={isChecked.avaliable} onChange={handleChange}/>
                            <span>avaliable</span>
                        </label>
                        <label>
                            <input type="checkbox" name='notAvaliable' checked={isChecked.notAvaliable} onChange={handleChange}/>
                            <span>not avaliable</span>
                        </label>
                    </div>
                </div>
                <div className='checkbox-design-container'>
                    <h4>Choose brand</h4>
                    <div className='brand-container'>
                        {
                            uniqueBrands.map((brand, index) => (
                                <label key={index}>
                                    <input type="checkbox" name={brand} checked={isChecked[brand] || false} onChange={handleChange}/>
                                    <span>{brand}</span>
                                </label>
                            ))
                        }
                    </div>
                </div>
            </div>
            <div className='part-container'>
                <PartBox parts={finalPartList} setCartItemsProp={setCartItemsProp}/>
            </div>
        </div>
    );
}

export default PartContainer;
