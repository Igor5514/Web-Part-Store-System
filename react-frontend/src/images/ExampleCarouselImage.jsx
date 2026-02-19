
import '../components/Components.css';

const ExampleCarouselImage = ({ text,image }) => {
    return (
      <div className='center-flex'>
        <img src={image} alt={text} style={{ width: '100%' }}  />
      </div>
    );
  };

export default ExampleCarouselImage;