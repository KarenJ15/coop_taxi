import React from 'react';
import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import { Home } from "../src/components/Home";
import { TravelList } from "./components/travel/TravelList";
import { TravelForm } from "./components/travel/TravelForm";
import { TravelCard } from "./components/travel/TravelCard";

const title = "Cooperativa de taxis";
const description = "Aplicación web para la automatización de taxis en linea";

const App: React.FC = () => {
  return (
    <div>
      <nav className="navbar navbar-expand navbar-dark bg-dark">        
        <Link to={"/"}  className="navbar-brand">
          NRC 6515
        </Link>
        <div className="navbar-nav mr-auto">
          <li className="nav-item">
            <Link to={"/travels"} className="nav-link">
              Viajes
            </Link>
          </li>          
        </div>
      </nav>
      <div className="container mt-3">
        <Routes>
          <Route path="/" element={<Home title={title} description={description} />} />          
          <Route path="/travels" element={<TravelList />} />          
          <Route path="/travels/create" element={<TravelForm />} />    
          <Route path="/travels/retrieve/:id" element={<TravelCard/>} />      
          <Route path="/travels/update/:id" element={<TravelForm />} />    
        </Routes>
      </div>
    </div>
  );
}
export default App;