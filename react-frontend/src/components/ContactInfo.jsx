import "./Components.css"

function ContactInfo(){
    return (
        <div className="contact-info-container">
            <h4 style={{borderBottom: "1px solid white", width:"100%",background: "#1a1a1a"}} className="p-2">Contact info</h4>
            <p className="mt-3" style={{background: "#1a1a1a"}}>email: <u style={{background: "#1a1a1a"}}>jeremicigor2003@gmail.com</u></p>
            <p className="mt-3" style={{background: "#1a1a1a"}}>phone number: <u style={{background: "#1a1a1a"}}>0611067699</u></p>
            <p className="mt-3" style={{background: "#1a1a1a"}}>location: <u style={{background: "#1a1a1a"}}>nikole kopernika 35</u></p>
            <iframe
                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2921.8488048950453!2d21.918669776015438!3d43.315318774218824!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4755b093fa977461%3A0x416d5a300ebf12fa!2sNikole%20Kopernika%2C%20Ni%C5%A1!5e1!3m2!1sen!2srs!4v1737491427178!5m2!1sen!2srs"
                style={{ border: 0 }}
                allowFullScreen=""
                loading="lazy"
                referrerPolicy="no-referrer-when-downgrade"
                title="Embedded Map"
                className="google-map"
                ></iframe>       
        </div>
    );

}

export default ContactInfo;
