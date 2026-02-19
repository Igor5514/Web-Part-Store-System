import { useState, useEffect } from "react";
import "./Components.css";
import RegisterDropdown from "./RegisterDropdown";

const Registration = ({ setLoginVisibility, setRegistrationVisibility }) => {

    const [formData, setFormData] = useState({
        fullName: "",
        email: "",
        password: "",
        repeatPassword: "",
        phoneNumber: "",
        role:"user",
    });

    const address = "http://localhost:8080/users";

    const [errors, setErrors] = useState({});

    async function checkIfExists(email){
        const response = await fetch(`${address}/getResByEmail`,{
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({email: email}),
        });
        const exist = await response.json()
        console.log(exist);
        return exist;
    }

    function validateFullName() {
        if (!formData.fullName.trim()) {
            return "Full name is required.";
        }
        if (formData.fullName.length < 3) {
            return "Full name must be at least 3 characters.";
        }
        return "";
    }

    async function validateEmail() {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!formData.email.trim()) {
            return "Email is required.";
        }
        if (!emailRegex.test(formData.email)) {
            return "Invalid email format.";
        }
        let emailExist = await checkIfExists(formData.email)
        if(emailExist) {
            return "Email already exists.";
        }
        return "";
    }
    

    function validatePassword() {
        if (formData.password.length < 6) {
            return "Password must be at least 6 characters.";
        }
        return "";
    }

    function validateRepeatPassword() {
        if (formData.repeatPassword !== formData.password) {
            return "Passwords do not match.";
        }
        return "";
    }

    function validatePhoneNumber() {
        const phoneRegex = /^[0-9]{10,15}$/;
        if (!phoneRegex.test(formData.phoneNumber)) {
            return "Phone number must be 10-15 digits.";
        }
        return "";
    }

    function handleInputChange(event) {
        setFormData({ ...formData, [event.target.name]: event.target.value });
    }

    async function handleRegisterClick(event) {
        event.preventDefault();
    
        const newErrors = {
            fullName: validateFullName(),
            email: await validateEmail(),
            password: validatePassword(),
            repeatPassword: validateRepeatPassword(),
            phoneNumber: validatePhoneNumber(),
        };
        setErrors(newErrors);
        try {
            if (Object.values(newErrors).every((error) => error === "")) {
                const response = await fetch(`${address}/add`, {
                    method: "POST",
                    headers: {
                        'Content-Type': 'application/json' 
                    },
                    body: JSON.stringify(formData),
                })
                const data = await response.json();
                if(response.ok) {
                    console.log("Success: "+ data.message);
                    setLoginVisibility(true);
                    setRegistrationVisibility(false);
                }else{
                    console.error(data.message);
                }
            }
            
        }catch (e) {
            console.error(e.message);
        }
       
    }

    function handleLoginClick(event) {
        event.preventDefault();
        setLoginVisibility(true);
        setRegistrationVisibility(false);
    }

    function handleGuestClick(event) {
        event.preventDefault();
        setLoginVisibility(false);
        setRegistrationVisibility(false);
    }

    return (
        <div className="d-flex justify-content-center main-logreg-container" style={{ fontFamily: "'DM Sans', sans-serif" }}>
            <div className="login-container">
                <h2 className="text-center pt-3" style={{ backgroundColor: " #1a1a1a" }}>Registration</h2>

                <div className="mb-3 component-bg" style={{ padding: "0 2em 0 2em" }}>
                    <label htmlFor="fullName" className="mb-1 component-bg">Full Name:</label>
                    <input type="text" id="fullName" name="fullName" className="form-control bg-secondary text-light border-0" 
                           value={formData.fullName} onChange={handleInputChange} required />
                    {errors.fullName && <p className="text-danger">{errors.fullName}</p>}
                </div>

                <div className="mb-3 component-bg" style={{ padding: "0 2em 0 2em" }}>
                    <label htmlFor="email" className="mb-1 component-bg">User Email:</label>
                    <input type="text" id="email" name="email" className="form-control bg-secondary text-light border-0" 
                           value={formData.email} onChange={handleInputChange} required />
                    {errors.email && <p className="text-danger">{errors.email}</p>}
                </div>

                <div className="mb-3 component-bg" style={{ padding: "0 2em 0 2em" }}>
                    <label htmlFor="password" className="mb-1 component-bg">User Password:</label>
                    <input type="password" id="password" name="password" className="form-control bg-secondary text-light border-0" 
                           value={formData.password} onChange={handleInputChange} required />
                    {errors.password && <p className="text-danger">{errors.password}</p>}
                </div>

                <div className="mb-3 component-bg" style={{ padding: "0 2em 0 2em" }}>
                    <label htmlFor="repeatPassword" className="mb-1 component-bg">Repeat Password:</label>
                    <input type="password" id="repeatPassword" name="repeatPassword" className="form-control bg-secondary text-light border-0" 
                           value={formData.repeatPassword} onChange={handleInputChange} required />
                    {errors.repeatPassword && <p className="text-danger">{errors.repeatPassword}</p>}
                </div>

                <div className="mb-3 component-bg" style={{ padding: "0 2em 0 2em" }}>
                    <label htmlFor="phoneNumber" className="mb-1 component-bg">Phone Number:</label>
                    <input type="text" id="phoneNumber" name="phoneNumber" className="form-control bg-secondary text-light border-0" 
                           value={formData.phoneNumber} onChange={handleInputChange} required />
                    {errors.phoneNumber && <p className="text-danger">{errors.phoneNumber}</p>}
                </div>

                <div className="mb-3 component-bg" style={{ padding: "0 2em 0 2em" }}>
                    <RegisterDropdown className="component-bg" setRole={(role) => setFormData({ ...formData, role })} />
                </div>

                <div className="d-flex justify-content-center" style={{ backgroundColor: " #1a1a1a" }}>
                    <div className="login-buttons text-white">
                        <button className="button-class-login" onClick={handleLoginClick}>Login</button>
                        <label className="component-bg">Don't have an account?</label>
                        <button className="button-class-login" onClick={handleRegisterClick}>Register</button>
                        <button className="button-class-login" onClick={handleGuestClick}>Continue as Guest</button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Registration;
