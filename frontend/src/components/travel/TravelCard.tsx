import { useEffect, useState } from "react";
import { FaArrowLeft, FaTrash } from "react-icons/fa";
import { Link, useParams } from "react-router-dom";
import ITravelModel from "../../models/Travel";
import TravelService from "../../services/TravelService";
export const TravelCard = () => {
  const { id }= useParams();

  const [travel, setTravel] = useState<ITravelModel>();

  useEffect(() => {
    if (id)
      getTravel(id);
  }, [id]);


  const getTravel = (id: any) => {
    TravelService.retrieve(id)
      .then((response: any) => {
        setTravel(response.data); //Víncula el resultado del servicio con la función del Hook useState
        console.log(response.data);
      })
      .catch((e: Error) => {
        console.log(e);
      });
 };

    return (
      <div>
      { 
        travel ? (
          <div>          
          <h2>{travel.origin}</h2>
          <p>{travel.destination}</p>
          <ul>
            <li> <strong>Documento (doc) :</strong>  {travel.cost}</li>
          </ul>
          <br />
							<div className="btn-group" role="group">								
                <Link to={"/drivers"} className="btn btn-primary">
                    <FaArrowLeft /> Volver
                </Link>
				<button type="button" className="btn btn-danger">
                <FaTrash />Eliminar
                </button>
							</div>
          </div>

        ) : 
        ( 
          <h1>No hay un viaje activo</h1>
        )
      }
      </div>
    );
}