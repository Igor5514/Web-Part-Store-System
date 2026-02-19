import ContactForm from "../components/ContactForm";
import "../fonts/fonts.css";
import ContactInfo from "../components/ContactInfo";
import "../components/Components.css"

export const Contact = () => {
    return (
        <div className="main-contact-container">
            <div className='d-flex justify-content-center contact-container' >
                <ContactForm />
                <ContactInfo />
            </div>
        </div>
    );




}