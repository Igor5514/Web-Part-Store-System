import React from 'react';
import "./ShopComponents.css";
import noImage from '../images/noImage.png'

const CartOverlay = ({isOpen, onClose, cartItems = [] }) => {
  if (!isOpen) return null;

  return (
    <>
      <div className="overlay-background"  onClick={onClose}></div>
      <div className="cart-overlay" style={{ fontFamily: "'DM Sans', sans-serif" }}>
        <button className="close-button" onClick={onClose}>×</button>
        <h2 style={{ backgroundColor:' #1a1a1a'}}>Your Cart</h2>
        <div className="cart-items">
          {cartItems.length === 0 ? (
            <p>Your cart is empty.</p>
          ) : (
            cartItems.map((item, index) => (
              <div className="cart-item" key={index}>
                <img src={item.image !== null ? item.image : noImage} width={'15%'} />
                <div className='cart-description'>
                    <p>item name: {item.brand +" "+item.partNumber+" "+item.name}</p>
                    <p>price: {item.price}$</p>
                    <div className='d-flex d-inline-flex'>
                        <p>quantity:  </p>
                        <input type='number' defaultValue={1} className='cart-number-input'/>
                    </div>
                </div>
              </div>
            ))
          )}
        </div>
        <button className='order-button'>proceed</button>
      </div>
    </>
  );
};

export default CartOverlay;
