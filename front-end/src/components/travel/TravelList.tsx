import { FaPen, FaEye, FaTrash, FaPlus } from "react-icons/fa";
import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom';
import ITravelModel from '../../models/Travel';
import TravelService from '../../services/TravelService';
import Swal from "sweetalert2";
import ReactPaginate from "react-paginate";

export const TravelList = () => {

    //Hook: Define un atributo y la función que lo va a actualizar
    const [travel, setTravel] = useState<Array<ITravelModel>>([]);
    const [itemsCount, setItemsCount] = useState<number>(0);
    const [pageCount, setPageCount] = useState(0);
    const [itemsPerPage, setItemsPerPage] = useState(5);
    
    //Hook para llamar a la Web API
    useEffect(() => {
      getItems();  
      listTravels(0, itemsPerPage);           
      }, [ ]);

    const handlePageClick = (event: any) => {        
      const numberPage = event.selected;                   
      listTravels(numberPage, itemsPerPage);
    };

    //Función que llama al Service para listar los datos desde la Web API
    const listTravels = (page: number, size: number) => {
      TravelService.list(page, size)
         .then((response: any) => {
           setTravel(response.data); //Víncula el resultado del servicio con la función del Hook useState
           console.log(response.data);
         })
         .catch((e: Error) => {
           console.log(e);
         });
    };

    const getItems = () => {
      TravelService.count().then((response: any) =>{
        var itemsCount = response;
        setItemsCount(itemsCount);
        setPageCount(Math.ceil(itemsCount/ itemsPerPage));           
        setItemsPerPage(5)
        console.log(response);
      }).catch((e : Error)=> {
        console.log(e);
      });
    }

    const removeTravel = (id: number) => {
        Swal.fire({
            title: '¿Desea eliminar el viaje?',
            showDenyButton: true,
            confirmButtonText: 'Si',
            denyButtonText: 'No',
          }).then((result) => {            
            if (result.isConfirmed) {
                TravelService.remove(id)
                .then((response: any) => {
                  listTravels(0,itemsPerPage);
                  console.log(response.data);
                })
                .catch((e: Error) => {
                  console.log(e);
                });      

            }
          });        
     };
   
    return ( 
        <div className='list row'>
            <h1>Hay {itemsCount} viajes</h1>
            <div className='col-md-12'>
                <table className='table'>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Origen</th>
                            <th>Destino</th>
                            <th>Costo</th>
                            <th># Clientes</th>
                            <th>
                              <Link to={"/travels/create"} className="btn btn-success">
                                  <FaPlus /> Agregar
                              </Link>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        {travel && travel.map((Travel, index) => (                          
                            <tr key={index}>
                                <td>{++index}</td>
                                <td>{Travel.origin}</td>
                                <td>{Travel.destination}</td>
                                <td>{Travel.cost} </td>
                                <td>
                        
                                <div className="btn-group" role="group">
                                <Link to={"/travels/retrieve/" + Travel.id} className="btn btn-warning">
                                    <FaEye /> Ver
                                  </Link>                                  
                                  <Link to={"/travels/update/" + Travel.id} className="btn btn-primary">
                                      <FaPen /> Editar
                                  </Link>

                                  <button className="btn btn-danger" onClick={() => removeTravel(Travel.id!)}>
                                    <FaTrash /> Eliminar
                                  </button>

                                  
                                </div>
                                    
                                </td>
                            </tr>                        
                        ))}
                    </tbody>
                </table>

                <ReactPaginate
                  className="pagination"
                  breakLabel="..."
                  nextLabel="siguiente >"
                  onPageChange={handlePageClick}
                  pageRangeDisplayed={5}
                  pageCount={pageCount}
                  previousLabel="< anterior"/>

            </div>            
        </div>
     );
}