import { useState } from 'react';
import Carousel from 'react-bootstrap/Carousel';
import ExampleCarouselImage from '../images/ExampleCarouselImage';
import firstSliderImage from '../images/KSE_0797.jpg';
import secondSliderImage from '../images/sliderImage2.jpg';
import thirdSliderImage from '../images/sliderImage3.jpg';
import './Components.css';

function ControlledCarousel() {
  const [index, setIndex] = useState(0);

  const handleSelect = (selectedIndex) => {
    setIndex(selectedIndex);
  };

  return (
    <Carousel style={{ width: '86%',border: '2px solid white', marginTop: "5px" }} activeIndex={index} onSelect={handleSelect}>
      <Carousel.Item>
        <ExampleCarouselImage text="First slide" image={firstSliderImage} />
        <Carousel.Caption>
          <h3>Expert Repairs You Can Trust</h3>
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item>
        <ExampleCarouselImage text="Second slide" image={secondSliderImage} />
        <Carousel.Caption>
          <h3>Your Car, Our Priority</h3>
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item>
        <ExampleCarouselImage text="Third slide" image={thirdSliderImage} />
        <Carousel.Caption>
          <h3>Shop Quality Auto Parts Online</h3>
        </Carousel.Caption>
      </Carousel.Item>
    </Carousel>
  );
}

export default ControlledCarousel;
