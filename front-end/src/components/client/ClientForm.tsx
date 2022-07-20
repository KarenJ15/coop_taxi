import { ChangeEvent, useEffect, useState } from "react";
import { FaArrowLeft, FaSave } from "react-icons/fa";
import { Link, useNavigate, useParams } from "react-router-dom";
import IClientModel from "../../models/Client";
import ClientService from "../../services/ClientService";

export const ClientForm = () => {
	
  const { id }= useParams();
  let navigate = useNavigate();

    //Model vacío
    const initialClientModel : IClientModel = {
        id: null,
        name: "",
        cellphone: "",
        direction: "",
        createdDate: 14.05,
        createdBy:" ",
        updateDate: 18.05,
        updatedBy: ""
    };

    //Hooks para gestionar el modelo
    const [client, setClient] = useState<IClientModel>(initialClientModel);
    
    //Escucha los cambios en cada control Input y los asigna a los valores del Modelo
    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setClient({ ...client, [name]: value });
    };

		const handleTextAreaChange = (event: ChangeEvent<HTMLTextAreaElement>) => {
			const { name, value } = event.target;
			setClient({ ...client, [name]: value });
	};

    const saveClient = () => {        
      if(client.id !== null)
      {
        ClientService.update(client)
        .then((response: any) => {
          navigate("/clients");
          console.log(response.data);
        })
        .catch((e: Error) => {
          console.log(e);
        });
      }
      else
      {
			  ClientService.create(client)
          .then((response: any) => {    
            navigate("/clients");
            console.log(response.data);
          })
          .catch((e: Error) => {
            console.log(e);
          });
      }
    };

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


		return ( //JSX
			<div className="submit-form">				
					<div>
						{ client.id !== null ? (<h1>Actualizado cliente {client.name}</h1>) : (<h1>Registro de nuevo cliente</h1>) }            
						<div className="form-group">
						<label htmlFor="name">Nombre</label>
            <input
              type="text"
							placeholder="Ingrese el nombre del cliente"
              className="form-control"
              id="name"
              required
              value={client.name}
              onChange={handleInputChange}
              name="Nombre"
            />
						<label htmlFor="model">Modelo</label>
            <input						
              type="text"
              className="form-control"
							placeholder="Ingrese el celular del cliente"
              id="cellphone"
              required
              value={client.cellphone}
              onChange={handleInputChange}
              name="cellphone"
            />
						<label htmlFor="registration">Numero </label>
            <input						
              type="text"
              className="form-control"
              id="direction"
              required
              value={client.direction}
              onChange={handleInputChange}
              name="direccion"
            />
            <label htmlFor="CreatedDate">Fecha en que se creo</label>
            <input						
              type="text"
              className="form-control"
              id="createdDate"
              required
              value={client.createdDate}
              onChange={handleInputChange}
              name="createdDate"
            />
            <label htmlFor="CreatedBy">Creado por</label>
            <input						
              type="text"
              className="form-control"
              id="createdBy"
              required
              value={client.createdBy}
              onChange={handleInputChange}
              name="createdBy"
            />
             <label htmlFor="updateDate">Actualizado en</label>
            <input						
              type="number"
              className="form-control"
              id="updateDate"
              required
              value={client.updateDate}
              onChange={handleInputChange}
              name="updateDate"
            />
             <label htmlFor="updateBy">Actualizado por</label>
            <input						
              type="text"
              className="form-control"
              id="updateBy"
              required
              value={client.updatedBy}
              onChange={handleInputChange}
              name="updateBy"
            />
						<br />
							<div className="btn-group" role="group">								
                <Link to={"/clients"} className="btn btn-primary">
                    <FaArrowLeft /> Volver
                </Link>
								<button type="button" onClick={saveClient} className="btn btn-success">
                  <FaSave />Guardar
                </button>
							</div>
						</div>
					</div>				
			</div>        
    );

}