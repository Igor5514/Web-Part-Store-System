import { StrictMode } from 'react'
import { BrowserRouter } from 'react-router-dom';
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import 'bootstrap/dist/css/bootstrap.min.css';
import { VehicleProvider } from './context/VehicleProvider.jsx';

createRoot(document.getElementById('root')).render(
  <VehicleProvider>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </VehicleProvider>

)

