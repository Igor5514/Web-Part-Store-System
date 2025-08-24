import { UserContext } from "../context/UserProvider";
import "./Components.css"
import React, { useState,useContext } from "react";

const Login = ({setLoginVisibility, setRegistrationVisibility}) => {
    const {setUser} = useContext(UserContext);

    const [formData, setFormData] = useState({
        email: "",
        password: ""
    });

    const [errors, setErrors] = useState({});
    
    function validateEmail(){
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if(!formData.email.trim()){
            return "email is required";
        }
        if(!emailRegex.test(formData.email)){
            return "invalid email format"
        }
        return "";
    }

    function validatePassword(){
        if(!formData.password.trim()){
            return "password is required"
        }
        if (formData.password.length < 6) {
            return "Password must be at least 6 characters.";
        }
        return "";
    }


    async function handleLoginClick(event) {
        event.preventDefault();

        const newErrors = {
            email: validateEmail(),
            password: validatePassword()
        }

        setErrors(newErrors);

        if (Object.values(newErrors).every((error) => error === "")) {
            const response = await fetch("http://localhost:8080/users/getByEmail", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(formData)
            })
            const data = await response.json();
            if(response.ok){
                setUser({
                    fullName: data.fullName,
                    email: data.email,
                    phoneNumber: data.phoneNumber,
                    role: data.role,
                    isLoggedIn: true
                });
                setLoginVisibility(false);
                setRegistrationVisibility(false);
            }else{
                console.error("Error", data.message);

                setErrors((prevErrors) => ({
                    ...prevErrors,
                    password: data.message
                }));
            }
        }

    }

    function handleRegisterClick(event) {
        event.preventDefault();
        setLoginVisibility(false);
        setRegistrationVisibility(true);
    }

    function handleGuestClick(){
        setLoginVisibility(false);
        setRegistrationVisibility(false);
    }

    function handleInputChange(event){
        setFormData({...formData, [event.target.name]: event.target.value});
    }

    return (
        <div className="d-flex justify-content-center custom-font main-logreg-container" style={{ fontFamily: "'DM Sans', sans-serif" }}>
            <div className="login-container">
                <h2 className="text-center pt-3" style={{backgroundColor: " #1a1a1a"}}>Login</h2>
                <div className='mb-3 component-bg' style={{padding:"0 2em 0 2em"}}>
                    <label htmlFor="email" className='mb-1 component-bg' style={{fontSize: "1.2em", padding:"0.2em 1em"}}>user email:</label><br />
                    <input type="text" id="email" name="email" style={{backgroundColor: "black"}} className="form-control bg-secondary text-light border-0" 
                    value={formData.email} onChange={handleInputChange} required />
                    {errors.email && <p className="text-danger">{errors.email}</p>}
                </div>
                <div className='mb-3 component-bg' style={{padding:"0 2em 0 2em"}}>
                    <label htmlFor="password" className='mb-1 component-bg' style={{fontSize: "1.2em", padding:"0.2em 1em"}}>user password:</label><br />
                    <input type="password" id="password" name="password" style={{backgroundColor: "black"}} className="form-control bg-secondary text-light border-0"
                    value={formData.password}  onChange={handleInputChange} required />
                    {errors.password && <p className="text-danger">{errors.password}</p>}
                </div>
                <div className="d-flex justify-content-center" style={{backgroundColor: " #1a1a1a"}}>
                    <div className="login-buttons text-white"> 
                        <button className="button-class-login" onClick={(event) => handleLoginClick(event)}>login</button>
                        <label htmlFor="firstName" className='component-bg'>don't have an account?</label>
                        <button className="button-class-login" onClick={(event) => handleRegisterClick(event)}>register</button>
                        <button className="button-class-login" onClick={(event) => handleGuestClick(event)}>continue as guest</button>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Login;