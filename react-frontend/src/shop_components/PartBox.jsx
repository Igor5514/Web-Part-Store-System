import { useState, useEffect, useContext } from 'react';
import "./ShopComponents.css";
import noImage from '../images/noImage.png'
import phoneIcon from '../images/phone-icon.png'
import boxIcon from '../images/green-box-icon.png'
import redBoxIcon from '../images/red-box-icon.png'
import { useVehicle }  from "../context/VehicleProvider";
import {UserContext } from "../context/UserProvider"

const PartBox = ({ parts = [], setCartItemsProp }) => {
    const { vehicle } = useVehicle();
    const { user } = useContext(UserContext);

    if (vehicle.make === '') {
        return (
        <p className='error-paragraph'>
            please choose a vehicle
        </p>
        )
    }

    if (parts.length === 0) {
        return (
        <p className='error-paragraph'>
            we couldn't find matching parts
        </p>
        )
    }

    const addToCartEvent = (event, cartItem) => {
        event.preventDefault();
        setCartItemsProp(cartItem);
        addToCart(cartItem);
        
    }

    const addToCart = async (cartItem) => {
        console.log(user.email)
        console.log(cartItem.partId)
        try{
            const response = await fetch(`http://localhost:8080/parts/postCartItem?email=${user.email}&partId=${cartItem.partId}`, {
                method: 'POST',
                headers: {
                    'Authorization' : `Bearer ${user.token}`
                }
            })
            const result = await response.text();
            if(response.ok){
                alert(cartItem.name + ' added to cart!')
            }
            console.log(result);
        }catch(error){
            console.error(error);
        }
        
    }

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
                                    <li style={{backgroundColor: " #262626"}}>part number: {Item.partNumber}</li>
                                    <li style={{backgroundColor: " #262626"}}>brand: {Item.brand}</li>
                                    <li style={{backgroundColor: " #262626"}}>weight: {Item.weight}</li>
                                    <li style={{backgroundColor: " #262626"}}>dimensions: {Item.dimensions}</li>
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
                                <button onClick={e => addToCartEvent(e, Item)} className='add-to-cart-button'>add to cart</button>
                            </div>
                        </div>
                    )
                }) 
            }
            
        </>
    )
}

export default PartBox;