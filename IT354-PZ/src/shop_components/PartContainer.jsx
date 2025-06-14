import { useState, useEffect } from 'react';
import "./ShopComponents.css";
import PartBox from "./PartBox.jsx"

const PartContainer = ({setPageCountProp}) => {
    
    const [isChecked, setIsChecked] = useState({
        avaliable: false,
        notAvaliable: false,
        brand1: false,
        brand2: false,
        brand3: false,
        brand4: false,
    });
    
    const handleChange = (event) => {
        const {name, checked} = event.target
        setIsChecked((prev) => ({
            ...prev, [name]: checked
        }))
    };

    return(
        <>
            <div className='parts-component' style={{ fontFamily: "'DM Sans', sans-serif", marginTop: "3em"} } >
                <div className='chekbox-containers text-white' >
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
                            <label>
                                <input type="checkbox" name='brand1' checked={isChecked.brand1} onChange={handleChange}/>
                                <span>brand1</span>
                            </label>
                            <label>
                                <input type="checkbox" name='brand2' checked={isChecked.brand2} onChange={handleChange}/>
                                <span>brand2</span>
                            </label>
                            <label>
                                <input type="checkbox" name='brand3' checked={isChecked.brand3} onChange={handleChange}/>
                                <span>brand3</span>
                            </label>
                            <label>
                                <input type="checkbox" name='brand4' checked={isChecked.brand4} onChange={handleChange}/>
                                <span>brand4</span>
                            </label>
                        </div>
                    </div>
                </div>
                <div className='part-container'>
                    <PartBox />
                </div>
        
            </div>
        </>
    )
}

export default PartContainer;