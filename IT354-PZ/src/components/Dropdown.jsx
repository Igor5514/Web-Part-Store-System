import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import "./Components.css"
import { UserContext } from '../context/UserProvider.jsx'
import { useContext } from 'react';

function Dropdown({setLoginVisibility, setRegistrationVisibility}) {
  const {user, setUser} = useContext(UserContext)

  function handleClickLogin(event){
    event.preventDefault();
    setLoginVisibility(true);
  }

  function handleClickReg(event){
    event.preventDefault();
    setRegistrationVisibility(true);
  }

  function handleSignOutClick(event){
    event.preventDefault();
    if(user.isLoggedIn === true){
      setUser(prev => ({
        fullName: '',
        email: '',
        phoneNumber: '',
        isLoggedIn: false
      }))
      alert('you logged out')
    }else{
      alert('you must be logged in to sign out')
    }
  }

  return (
    <Navbar variant="dark" bg="dark" style={{padding: "0"}} >
      <Container fluid style={{padding: "0"}} >
        <Navbar.Toggle aria-controls="navbar-dark-example"  />
        <Navbar.Collapse id="navbar-dark-example">
          <Nav>
            <NavDropdown id="nav-dropdown-dark-example" title="Account" menuVariant="dark">
              <NavDropdown.Item onClick={(event) => {handleClickLogin(event)}} href="#action/3.1">Login</NavDropdown.Item>
              <NavDropdown.Item onClick={(event) => {handleClickReg(event)}} href="#action/3.2">Registration</NavDropdown.Item>
              <NavDropdown.Item href="#action/3.3">Modify Account</NavDropdown.Item>
              <NavDropdown.Item onClick={(event) => handleSignOutClick(event)} href="#action/3.4">sign out</NavDropdown.Item>
            </NavDropdown>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default Dropdown;