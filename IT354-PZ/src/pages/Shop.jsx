import { useState, useEffect, useRef } from 'react';
import FirstCard from "../shop_components/FirstCard";
import SecondCard from "../shop_components/SecondCard"
import "./pages.css"
import CarSelectionMenu from "../shop_components/CarSelectionMenu.jsx";
import image2 from "../images/service.jpg";
import image3 from "../images/cart.png";
import PartContainer from '../shop_components/PartContainer.jsx';

export const Shop = () => {
    const [group, setGroup] = useState([]);
    const [partsList, setPartsList] = useState([]);
    const [isFirstCardContainerActive, setIsFirstCardContainerActive] = useState(true);
    const [isPartGroupContainerActive, setIsPartGroupContainerActive] = useState(true);
    const [selectedGroup, setSelectedGroup] = useState("");
    const [selectedCar, setSelectedCar] = useState({});

    function setFirstCardActive(value){
        setIsFirstCardContainerActive(value)
    }

     function setPartGroupContainerActive(value){
        setIsPartGroupContainerActive(value)
    }


    useEffect(() => {
        async function loadGroupComponents() {
            if(isFirstCardContainerActive){
                try {
                    const response = await fetch("http://localhost:8080/vehicle/getPartGroup");
                    const data = await response.json();
    
                    if (response.ok) {
                        console.log("data: ", data);
                        setGroup(data);
                    } else {
                        console.error(data.error);
                    }
                } catch (error) {
                    console.log(error.message);
                }
            }
        }
        
        loadGroupComponents();
    }, [isFirstCardContainerActive]);

    const handleGoBack = () => {
        if(!isFirstCardContainerActive){
            setPartsList([]);
            setIsFirstCardContainerActive(true)
        }
    }

    return (
        <>
            <div className="main-shop-container">
                <div className='shop-header' style={{display: "flex", flexDirection: "column"}}>
                    <div className='header-container'>
                        <button className='header-button back-button' onClick={handleGoBack}>Go back</button>
                        <div className='search-container'>
                            <input type="text" name="part-input" className=" bg-secondary text-light border-0" />
                            <button className='header-button'  >Search</button>
                        </div>
                        <button className='header-button'>Cart  <img src={image3} style={{width: "1.5em"}}/></button>
                    </div>
                    
                    <h3 className='selected-car-title'>Car: </h3>
                </div>
                
                <div className="main-menu-container">
                    <CarSelectionMenu />
                    <div className="image-container">
                        <img src={image2} alt="Service" />
                        <p className="image-title">Auto Parts & Accessories</p>
                    </div>
                </div>
                <h3 className="shop-title">Order best quality parts online</h3>

                !isPartGroupContainerActive && <PartContainer setPartGroupContainerActive={setPartGroupContainerActive} />
                isPartGroupContainerActive && <div className="part-carts-grid-container">
                    {
                        isFirstCardContainerActive && group.length > 0 ? group.map((item, index) => {
                            const img = `data:image/png;base64,${item.groupImage}`;
                            return (
                                <FirstCard key={index} title={item.groupName} paragraphs={item.partsList} img={img}
                                setFirstCardActive={setFirstCardActive} setPartsList={setPartsList}/>
                            );
                        }) : (
                            console.log("group cards inactive")
                        )
                    }
                    {
                        !isFirstCardContainerActive && partsList.length > 0 ? partsList.map((item, index) => {
                            const img = `data:image/png;base64,${item.groupImage}`;
                            return (
                                <SecondCard key={index} title={item.partName} img={img} setPartGroupContainerActive={setPartGroupContainerActive}/>
                            )
                        }) : (
                            console.log(partsList)
                        )

                    }
                </div>
            </div>
        </>
    );
}
