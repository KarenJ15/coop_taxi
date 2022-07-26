import React from 'react';
import ReactDOM from 'react-dom';
import { ChangeEvent, useEffect, useState } from "react";
import { FaArrowLeft, FaSave } from "react-icons/fa";
import { Link, useNavigate, useParams } from "react-router-dom";
import ITaxiModel from "../../models/Taxi";
import TaxiService from "../../services/TaxiService";

export const TaxiForm = () => {
	
  const { id }= useParams();
  let navigate = useNavigate();

    //Model vacío
    const initialTaxiModel : ITaxiModel = {
        id: null,
        licensePlate: "",
        model: "",
        tradeMark: "",
        registration: 2012,
        createdDate: " ",
        createdBy:" ",
        updatedDate: 14.05,
        updatedBy: ""
    };

    //Hooks para gestionar el modelo
    const [taxi, setTaxi] = useState<ITaxiModel>(initialTaxiModel);
    
    //Escucha los cambios en cada control Input y los asigna a los valores del Modelo
    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setTaxi({ ...taxi, [name]: value });
    };

		const handleTextAreaChange = (event: ChangeEvent<HTMLTextAreaElement>) => {
			const { name, value } = event.target;
			setTaxi({ ...taxi, [name]: value });
	};

    const saveTaxi = () => {        
      if(taxi.id !== null)
      {
        TaxiService.update(taxi)
        .then((response: any) => {
          navigate("/taxis");
          console.log(response.data);
        })
        .catch((e: Error) => {
          console.log(e);
        });
      }
      else
      {
			  TaxiService.create(taxi)
          .then((response: any) => {    
            navigate("/taxis");
            console.log(response.data);
          })
          .catch((e: Error) => {
            console.log(e);
          });
      }
    };

    useEffect(() => {
      if (id)
      getTaxi(id);
    }, [id]);


    const getTaxi = (id: any) => {
      TaxiService.retrieve(id)
        .then((response: any) => {
          setTaxi(response.data); //Víncula el resultado del servicio con la función del Hook useState
          console.log(response.data);
        })
        .catch((e: Error) => {
          console.log(e);
        });
   };


		return ( //JSX
			<div className="submit-form">				
					<div>
						{ taxi.id !== null ? (<h1>Actualizado taxi {taxi.licensePlate}</h1>) : (<h1>Registro de nuevo taxi</h1>) }            
						<div className="form-group">
						<label htmlFor="plate">Placa de matricula</label>
            <input
              type="text"
							placeholder="Ingrese la placa de matricula del taxi"
              className="form-control"
              id="plate"
              required
              value={taxi.licensePlate}
              onChange={handleInputChange}
              name="License Plate"
            />
						<label htmlFor="model">Modelo</label>
            <input						
              type="text"
              className="form-control"
							placeholder="Ingrese el modelo del taxi"
              id="model"
              required
              value={taxi.model}
              onChange={handleInputChange}
              name="Model"
            />
            <label htmlFor="tradeMark">Mark </label>
            <input						
              type="text"
              className="form-control"
              id="tradeMark"
              required
              value={taxi.tradeMark}
              onChange={handleInputChange}
              name="tradeMark"
            />
						<label htmlFor="registration">Matricula</label>
            <input						
              type="number"
              className="form-control"
              placeholder="Ingrese el modelo del taxi"
              id="registration"
              required
              value={taxi.registration}
              onChange={handleInputChange}
              name="Registration"
            />
            
						<br />
							<div className="btn-group" role="group">								
                <Link to={"/taxis"} className="btn btn-primary">
                    <FaArrowLeft /> Volver
                </Link>
								<button type="button" onClick={saveTaxi} className="btn btn-success">
                  <FaSave />Guardar
                </button>
							</div>
						</div>
					</div>				
			</div>        
    );

}