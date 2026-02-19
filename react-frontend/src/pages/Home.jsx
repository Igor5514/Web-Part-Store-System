import React from 'react';
import GroupCards from '../components/GroupCards';
import ControlledCarousel from '../components/ControlledCarousel';
import '../components/Components.css'
import Card from '../components/Card';
import "../fonts/fonts.css";

export const Home = () => {
    return (
        <div style={{ fontFamily: "'DM Sans', sans-serif" }} className='home-container'>
            
            <div className='slider-container'>
                <ControlledCarousel />
            </div>
            <h1 className='text-center text-white mt-5'>Our Expert Auto Services</h1>
            <div className='content-container d-flex justify-content-center' >
                <GroupCards />
            </div>
            <Card />
            <div className='footer d-flex justify-content-center mt-5' style={{width:"100%"}} >
                <p className="text-center text-white pt-2" style={{borderTop: "2px solid white", width:"80%"}}>@copyright by igor jeremic</p>        
            </div>
        </div>
    );




}