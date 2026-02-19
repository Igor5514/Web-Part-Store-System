import Card from 'react-bootstrap/Card';
import React from 'react';
import CardGroup from 'react-bootstrap/CardGroup';
import firstImage from '../images/car1.jpg'
import secondImage from '../images/car2.jpg'
import thirdImage from '../images/car3.jpg'

function GroupCards() {
  return (
    <CardGroup className='mt-5' style={{width: '75%'}}>
      <Card style={{marginRight: "1em", borderRadius: "20px", border: "1px solid white"}}>
        <Card.Img variant="top" src={firstImage}  />
        <Card.Body className='text-white' >
          <Card.Title>Expert Engine Repair</Card.Title>
          <Card.Text>
            Your vehicle’s engine is its heart, and we specialize in keeping it in top condition. From diagnostics 
            to full engine overhauls, our certified technicians provide reliable repairs to restore performance and 
            longevity. Drive with confidence, knowing your engine is in expert hands.
          </Card.Text>
        </Card.Body>
      </Card>
      <Card style={{marginRight: "1em", borderRadius: "20px", border: "1px solid white"}}>
        <Card.Img variant="top" src={secondImage} />
        <Card.Body className='text-white'>
          <Card.Title>Precision Brake Services</Card.Title>
          <Card.Text>
            Safety comes first with our top-notch brake services. Whether you need new brake pads, rotors, or a 
            complete system inspection, we ensure your vehicle stops on a dime. Trust us to maintain your brakes for
            smooth, worry-free driving.
          </Card.Text>
        </Card.Body>
      </Card>
      <Card style={{borderRadius: "20px", border: "1px solid white"}}>
        <Card.Img variant="top" src={thirdImage} />
        <Card.Body className='text-white'>
          <Card.Title>Comprehensive Transmission Care</Card.Title>
          <Card.Text>
            Keep your transmission running smoothly with our specialized care. From fluid changes to complex
            repairs, we tackle transmission issues with expertise and precision. Enjoy seamless gear shifts 
            and enhanced driving performance with our trusted service.
          </Card.Text>
        </Card.Body>
      </Card>
    </CardGroup>
  );
}

export default GroupCards;