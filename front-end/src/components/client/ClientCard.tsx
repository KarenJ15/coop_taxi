import { useEffect, useState } from "react";
import { FaArrowLeft, FaTrash } from "react-icons/fa";
import { Link, useParams } from "react-router-dom";
import IClientModel from "../../models/Client";
import ClientService from "../../services/ClientService";
export const ClientCard = () => {
  const { id }= useParams();

  const [client, setClient] = useState<IClientModel>();

  useEffect(() => {
    if (id)
      getClient(id);
  }, [id]);


  const getClient = (id: any) => {
    ClientService.retrieve(id)
      .then((response: any) => {
        setClient(response.data); //Víncula el resultado del servicio con la función del Hook useState
        console.log(response.data);
      })
      .catch((e: Error) => {
        console.log(e);
      });
 };

    return (
      <div>
      { 
        client ? (
          <div>          
          <h2>{client.name}</h2>
          <p>{client.direction}</p>
          <ul>
            <li> <strong>Celular (mat) :</strong>  {client.cellphone}</li>
            <li> <strong>Creado: (cre) :</strong>  {client.createdDate}</li>
            <li> <strong>Creado por: (cre) :</strong>  {client.createdBy}</li>
            <li> <strong>Actualizado: (act) :</strong>  {client.updateDate}</li>
            <li> <strong>Actualizado por: (act) :</strong>  {client.updatedBy}</li>
          </ul>
          <br />
							<div className="btn-group" role="group">								
                <Link to={"/clients"} className="btn btn-primary">
                    <FaArrowLeft /> Volver
                </Link>
				<button type="button" className="btn btn-danger">
                <FaTrash />Eliminar
                </button>
							</div>
          </div>

        ) : 
        ( 
          <h1>No hay un taxi activo</h1>
        )
      }
      </div>
    );
}