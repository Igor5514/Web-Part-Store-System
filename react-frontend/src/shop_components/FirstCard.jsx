import React from "react";
import "./ShopComponents.css";

const FirstCard = ({title, paragraphs, img, setPageCountProp, setPartsTypeList}) => {

    async function loadPartsType(groupName){
        try{
            const response = await fetch("http://localhost:8080/vehicle/getPartsList",{
                method: "POST",
                headers: {
                    "Content-Type" : "application/json"
                },
                body: JSON.stringify({groupName: groupName})
            })
            const data = await response.json();
            setPartsTypeList(data)
            if(response.ok){
                console.log("parts loaded successfully");
            }else{
                console.log("error " + data.message)
            }
        }catch (error){
            console.log(error.message)
        }
    }

     function buttonListener(e){
        e.preventDefault();
        const h2Title = e.target.closest("div").querySelector("h3").textContent;
        loadPartsType(h2Title)
        setPageCountProp(prev => prev +=1)
        
    }

    return(
        <div>
            <div className="main-card-container">
                <h3 style={{ fontFamily: "'DM Sans', sans-serif" }}>{title}</h3>
                <div className="sub-main-container">
                    <div className="main-paragraph-cotnainer">
                        {
                            paragraphs.map((paragraph, index) => (
                                <p key={index}>{paragraph}</p>
                            ))
                        }
                    </div>
                    <img src= {img} />
                </div>
                <button onClick={(e) => {buttonListener(e)}}>Show more</button>
            </div>
        </div>  
    );
}

export default FirstCard;