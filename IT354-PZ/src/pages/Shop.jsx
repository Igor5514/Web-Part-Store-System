import { useState, useEffect, useRef, useContext } from 'react';
import FirstCard from "../shop_components/FirstCard";
import SecondCard from "../shop_components/SecondCard"
import "./pages.css"
import CarSelectionMenu from "../shop_components/CarSelectionMenu.jsx";
import image2 from "../images/service.jpg";
import image3 from "../images/cart.png";
import PartContainer from '../shop_components/PartContainer.jsx';
import CartOverlay from '../shop_components/CartOverlay.jsx';
import { UserContext } from '../context/UserProvider.jsx'

export const Shop = () => {
    const [group, setGroup] = useState([]);
    const [partsTypeList, setPartsTypeList] = useState([]);
    const [pageCount, setPageCount] = useState(1);
    const [partList, setPartList] = useState([]);
    const [isCartOpen, setIsCartOpen] = useState(false);
    const [cartItems, setCartItems] = useState([]);
    const { user } = useContext(UserContext);

    const setCartItemsProp = (item) => {
        setCartItems(prev => [...new Set([...prev, item])]);
    }

    useEffect(() => {
        cartItems.map((item, index) => {
            console.log(item.name)
        })
        
    },[cartItems])

    const handleCartClick = () => {
        if(user.isLoggedIn === true){
            setIsCartOpen(true);
        }else{
            alert('login to continue')
        }
    };

    function setPageCountProp(value){
        setPageCount(value)
    }

    function setPartListProp(value){
        setPartList(value)
    }

    useEffect(() => {
        async function loadCartItems() {
            console.log(user.email);
            if (user.isLoggedIn) {
                try {
                    const response = await fetch(`http://localhost:8080/parts/getCartItem?email=${user.email}`,{
                        method: 'GET',
                        headers: {
                            'Authorization' : `Bearer ${user.token}`
                        }
                    });
                    console.log(user.token)
                    const data = await response.json();
                    if(response.ok){
                        setCartItems(data);
                    }
                } catch (error) {
                    console.error(error);
                }
                } else {
                setCartItems([]);
            }
        }
        loadCartItems();
    }, [user.isLoggedIn, user.email]);

    useEffect(() => {
        async function loadGroupComponents() {
            if(pageCount === 1){
                try {
                    const response = await fetch("http://localhost:8080/vehicle/getPartGroup");
                    const data = await response.json();
    
                    if (response.ok) {
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
    }, [pageCount]);

    const handleGoBack = () => {
        if(pageCount!=1){
            setPageCount(prev => prev -=1);
        }
    }

    return (
        <>
            <div className="main-shop-container">
                <CartOverlay isOpen={isCartOpen} onClose={() => setIsCartOpen(false)} cartItems={cartItems} />
                <div className='shop-header' style={{display: "flex", flexDirection: "column"}}>
                    <div className='header-container'>
                        <button className='header-button back-button' onClick={handleGoBack}>Go back</button>
                        <div className='search-container'>
                            <input type="text" name="part-input" className=" bg-secondary text-light border-0" />
                            <button className='header-button'  >Search</button>
                        </div>
                        <button className='header-button' onClick={handleCartClick}>Cart  <img src={image3} style={{width: "1.5em"}}/></button>
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

                {(pageCount === 3) && <PartContainer parts = {partList} setCartItemsProp={setCartItemsProp} />}
                
                {(pageCount === 1 || pageCount === 2) && <div className="part-carts-grid-container">
                    {
                        (pageCount === 1) && group.length > 0 ? group.map((item, index) => {
                            const img = `data:image/png;base64,${item.groupImage}`;
                            return (
                                <FirstCard key={index} title={item.groupName} paragraphs={item.partsList} img={img}
                                setPageCountProp={setPageCountProp} setPartsTypeList={setPartsTypeList} />
                            );
                        }) : (
                            console.log("group cards inactive")
                        )
                    }
                    {
                        (pageCount === 2) && partsTypeList.length > 0 ? partsTypeList.map((item, index) => {
                            const img = `data:image/png;base64,${item.groupImage}`;
                            return (
                                <SecondCard key={index} title={item.partName} img={img}
                                 setPageCountProp={setPageCountProp} setPartListProp={setPartListProp}/>
                            )
                        }) : (
                            console.log(partsTypeList)
                        )
                    }
                </div>}
            </div>
        </>
    );
}
