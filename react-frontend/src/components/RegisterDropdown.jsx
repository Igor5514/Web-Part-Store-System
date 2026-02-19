import { useState } from 'react';
import Dropdown from 'react-bootstrap/Dropdown';

function RegisterDropdown({ setRole }) {
  const [selectedRole, setSelectedRole] = useState("");

  function handleSelect(eventKey) {
    setSelectedRole(eventKey);
    setRole(eventKey); 
  }

  return (
    <Dropdown onSelect={handleSelect}>
      <Dropdown.Toggle variant="secondary" style={{ width: "100%" }}>
        {selectedRole || "Request role (optional)"}
      </Dropdown.Toggle>

      <Dropdown.Menu style={{ width: "100%" }}>
        <Dropdown.Item eventKey="Admin">Admin</Dropdown.Item>
        <Dropdown.Item eventKey="Seller">Seller</Dropdown.Item>
        <Dropdown.Item eventKey="Car Mechanic">Car Mechanic</Dropdown.Item>
      </Dropdown.Menu>
    </Dropdown>
  );
}

export default RegisterDropdown;
