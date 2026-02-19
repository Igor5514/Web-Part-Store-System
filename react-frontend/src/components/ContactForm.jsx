import {useState} from 'react'
import "./Components.css";

const ContactForm = () => {
    const [formData, setFormData] = useState({
        firstName: "",
        lastName: "",
        email: "",
        phone: "",
        message: "",
      });

    const handleChange = (e) => {
        const {name, value} = e.target;
        setFormData((prevData) => ({...prevData, [name]: value}));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log("Form submitted:", formData);
    };

    return (
        <div className='mt-4 component-bg contact-form-container'>
            <h2 className='text-white text-center' style={{backgroundColor: " #1a1a1a"}}>Ask us a question</h2>
            <form onSubmit={handleSubmit} className='text-white contact-form' style={{backgroundColor: " #1a1a1a"}} >
                <div className='mb-3 component-bg'>
                    <label htmlFor="firstName" className='mb-1 component-bg' style={{fontSize: "1.2em", padding:"0.2em 1em"}}>First Name:</label><br />
                    <input type="text" id="firstName" name="firstName" style={{backgroundColor: "black"}} className="form-control bg-secondary text-light border-0"
                    value={formData.firstName} onChange={handleChange} required />
                </div>
                
                <div className='mb-3 component-bg'>
                    <label htmlFor="lastName" className='mb-1 component-bg' style={{fontSize: "1.2em",padding:"0.2em 1em"}}>Last Name:</label><br />
                    <input type="text" id="lastName" name="lastName"  className="form-control bg-secondary text-light border-0" 
                    value={formData.lastName} onChange={handleChange} required />
                </div>

                <div className='mb-3 component-bg'>
                    <label htmlFor="email" className='mb-1 component-bg' style={{fontSize: "1.2em",padding:"0.2em 1em"}}>Email:</label><br />
                    <input type="email" id="email" name="email" className="form-control bg-secondary   text-light border-0"
                    value={formData.email} onChange={handleChange} required />
                </div>

                <div className='mb-3 component-bg'>
                    <label htmlFor="phone" className='mb-1 component-bg' style={{fontSize: "1.2em", padding:"0.2em 1em"}}>Phone Number:</label><br />
                    <input type="tel" id="phone" name="phone" className="form-control bg-secondary text-light border-0" 
                    value={formData.phone} onChange={handleChange} required />
                </div>

                <div className='component-bg'>
                    <label htmlFor="message" className='mb-1 component-bg' style={{fontSize: "1.2em", padding:"0.2em 1em"}}>Message:</label><br />
                    <textarea id="message" name="message" className="form-control bg-secondary text-light border-0" 
                    value={formData.message} onChange={handleChange} rows="4" required/>
                </div>

                <button type="submit" className="text-white border ms-4 mt-3" style={{padding:"0.2em 1em", backgroundColor: " #1a1a1a"}}>Submit</button>
            </form>
        </div>
    );
}

export default ContactForm;