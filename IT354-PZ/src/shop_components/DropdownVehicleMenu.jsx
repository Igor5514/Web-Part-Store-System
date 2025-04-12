import React, { useState, useEffect } from 'react';

const DropdownVehicleMenu = ({setValue,dropdownButtonText, dropdownItems, setIsDropdownClicked}) => { 
  const [dropdownText, setDropdownText] = useState(dropdownButtonText);
  const [items, setItems] = useState([]);
  const [inputValue, setInputValue] = useState("");
  const [isListPresent, setListPresent] = useState(false)

  useEffect(() => {
    setItems(dropdownItems);
  }, [dropdownItems])

  const onChangeInput = (e) => {
    const newValue = e.target.value;
    setInputValue(newValue);
  }

  const listSwitch = (e) => {
    e.preventDefault();
    if(isListPresent) {
      setListPresent(false);
      setIsDropdownClicked(false);
    }else{
      setListPresent(true);
      setIsDropdownClicked(true);
    }
  }

  return (
    <div className='dropdown-vehicle-container' >
      <button className="car-dropdown-display text-white fs-6 d-flex" onClick={e => listSwitch(e)} style={{ padding: "0.3em 0 0.3em 0.5em", backgroundColor:" #0d0d0d", border:"1px solid white"}}>
        <span style={{ flex: "1", textAlign:"left", backgroundColor:"  #0d0d0d"}}>{dropdownText}</span>
        <span style={{paddingRight:"0.5em", backgroundColor:"  #0d0d0d"}}>&#x25bc;</span>
      </button>
      <div className = {isListPresent ? `list-holder` : `none-display`} style={{backgroundColor: " #1a1a1a"}}>
        <div className='p-2' style={{backgroundColor: "black"}}>
          <input style={{ flex: "1", color:"white"}} className="car-dropdown-display" type="text" value={inputValue} onChange={(e) => onChangeInput(e)} />
        </div>
        <ul name="dropdown-input">
          {
            items.length > 0 && items.map((item,index) => <li onClick={() => {
              setValue(item);
              setListPresent(false);
              setDropdownText(item);
            }} key={index}>{item}</li>)
          }
        </ul>
      </div>
    </div>
  );
}

export default DropdownVehicleMenu;