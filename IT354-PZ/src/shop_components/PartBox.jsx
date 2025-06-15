import { useState, useEffect } from 'react';
import "./ShopComponents.css";
import firstImage from '../images/car1.jpg'
import phoneIcon from '../images/phone-icon.png'
import boxIcon from '../images/green-box-icon.png'

const PartBox = () => {
    return (
        <>
            <div className='part-box text-white'>
                <img src={firstImage} className='part-image p-3'/>
                <div className='part-description p-3'>
                    <h3 className='part-title ' style={{backgroundColor: " #1a1a1a"}}>Title</h3>
                    <ul className='text-white p-2' style={{listStyle: "none", backgroundColor: " #1a1a1a"}}>
                        <li style={{backgroundColor: " #1a1a1a"}}>nesto: nesto</li>
                        <li style={{backgroundColor: " #1a1a1a"}}>nesto: nesto</li>
                        <li style={{backgroundColor: " #1a1a1a"}}>nesto: nesto</li>
                        <li style={{backgroundColor: " #1a1a1a"}}>nesto: nesto</li>
                    </ul>
                </div>
                <div className='part-description-2 p-4'>
                    <p style={{backgroundColor: " #1a1a1a"}} className='m-1'>contact us and order </p>    
                    <h6 className='phone-title'>
                        <img src={phoneIcon} alt="Phone" width="25" height="25" style={{backgroundColor: " #1a1a1a"}} />
                        021-489-25-96
                    </h6>   
                    <p className='avaliability-paragraph'>
                         <img src={boxIcon} style={{backgroundColor: " #1a1a1a"}} width="25" height="25" />
                        avaliable
                    </p>
                    <p style={{fontSize: "1.2em", backgroundColor: " #1a1a1a", margin: "0"}}>Cena:</p>
                    <h3 style={{backgroundColor: " #1a1a1a"}} className='p-0'>72.00$</h3>
                    <button className='add-to-cart-button'>add to cart</button>
                </div>
            </div>
        </>
    )
}

export default PartBox;