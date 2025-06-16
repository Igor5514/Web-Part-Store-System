import { useState, useEffect } from 'react';
import "./ShopComponents.css";
import noImage from '../images/noImage.png'
import phoneIcon from '../images/phone-icon.png'
import boxIcon from '../images/green-box-icon.png'
import redBoxIcon from '../images/red-box-icon.png'

const PartBox = ({ parts = [] }) => {
    return (
        <>
            {
                parts.map((Item,index) => {
                    return(
                        <div className='part-box text-white' key={index}>
                            <img src={Item.image === null ? noImage : Item.image} className='part-image p-3'/>
                            <div className='part-description p-3'>
                                <h3 className='part-title ' style={{backgroundColor: " #1a1a1a"}}>{Item.brand  +" " + Item.partNumber + " " + Item.name}</h3>
                                <ul className='text-white p-2' style={{listStyle: "none", backgroundColor: " #1a1a1a"}}>
                                    <li style={{backgroundColor: " #1a1a1a"}}>part number: {Item.partNumber}</li>
                                    <li style={{backgroundColor: " #1a1a1a"}}>brand: {Item.brand}</li>
                                    <li style={{backgroundColor: " #1a1a1a"}}>weight: {Item.weight}</li>
                                    <li style={{backgroundColor: " #1a1a1a"}}>dimensions: {Item.dimensions}</li>
                                </ul>
                            </div>
                            <div className='part-description-2 p-4'>
                                <p style={{backgroundColor: " #1a1a1a"}} className='m-1'>contact us and order </p>    
                                <h6 className='phone-title'>
                                    <img src={phoneIcon} alt="Phone" width="25" height="25" style={{backgroundColor: " #1a1a1a"}} />
                                    021-489-25-96
                                </h6>   
                                <p className='avaliability-paragraph'>
                                    <img src={ (Item.stockQuantity> 0) ? boxIcon : redBoxIcon} style={{backgroundColor: " #1a1a1a"}} width="25" height="25" />
                                    {Item.stockQuantity > 0 ? "avaliable" : "not avaliable"}
                                </p>
                                <p style={{fontSize: "1.2em", backgroundColor: " #1a1a1a", margin: "0"}}>Cena:</p>
                                <h3 style={{backgroundColor: " #1a1a1a"}} className='p-0'>{Item.price}$</h3>
                                <button className='add-to-cart-button'>add to cart</button>
                            </div>
                        </div>
                    )
                })
            }
            
        </>
    )
}

export default PartBox;