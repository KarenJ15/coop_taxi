import { ChangeEvent, useEffect, useState } from "react";
import { FaArrowLeft, FaSave } from "react-icons/fa";
import { Link, useNavigate, useParams } from "react-router-dom";
import ITravelModel from "../../models/Travel";
import TravelService from "../../services/TravelService";

export const TravelForm = () => {
	
  const { id }= useParams();
  let navigate = useNavigate();

    //Model vacío
    const initialTravelModel : ITravelModel = {
      id: null,
      origin: "",
      destination: "",
      cost: 1.5,
    };

    //Hooks para gestionar el modelo
    const [travel, setTravel] = useState<ITravelModel>(initialTravelModel);
    
    //Escucha los cambios en cada control Input y los asigna a los valores del Modelo
    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setTravel({ ...travel, [name]: value });
    };

		const handleTextAreaChange = (event: ChangeEvent<HTMLTextAreaElement>) => {
			const { name, value } = event.target;
			setTravel({ ...travel, [name]: value });
	};

    const saveTravel = () => {        
      if(travel.id !== null)
      {
        TravelService.update(travel)
        .then((response: any) => {
          navigate("/travels");
          console.log(response.data);
        })
        .catch((e: Error) => {
          console.log(e);
        });
      }
      else
      {
			  TravelService.create(travel)
          .then((response: any) => {    
            navigate("/travels");
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
      TravelService.retrieve(id)
        .then((response: any) => {
          setTravel(response.data); //Víncula el resultado del servicio con la función del Hook useState
          console.log(response.data);
        })
        .catch((e: Error) => {
          console.log(e);
        });
   };


		return ( //JSX
			<div className="submit-form">				
					<div>
						{ travel.id !== null ? (<h1>Actualizado viaje {travel.destination}</h1>) : (<h1>Registro de nuevo viaje</h1>) }            
						<div className="form-group">
						<label htmlFor="plate">Destino</label>
            <input
              type="text"
							placeholder="Ingrese el destino del viaje"
              className="form-control"
              id="destino"
              required
              value={travel.destination}
              onChange={handleInputChange}
              name="Destino"
            />
						<label htmlFor="origin">Origen</label>
            <input						
              type="text"
              className="form-control"
							placeholder="Ingrese el origen del travel"
              id="origin"
              required
              value={travel.origin}
              onChange={handleInputChange}
              name="Origin"
            />
						<label htmlFor="cost">Costo </label>
            <input						
              type="float"
              className="form-control"
              id="costo"
							max="50"
							min="1.5"
              required
              value={travel.cost}
              onChange={handleInputChange}
              name="Costo"
            />
            
						<br />
							<div className="btn-group" role="group">								
                <Link to={"/travels"} className="btn btn-primary">
                    <FaArrowLeft /> Volver
                </Link>
								<button type="button" onClick={saveTravel} className="btn btn-success">
                  <FaSave />Guardar
                </button>
							</div>
						</div>
					</div>				
			</div>        
    );

}