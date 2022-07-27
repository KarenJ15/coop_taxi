import Swal from "sweetalert2";
import http from "../http-common";
import ITravelData from "../models/Travel";

const create = async (data: ITravelData) => {    
  try {
    const response = await http.post<ITravelData>("/travels", data);
    if(response.status === 201){
      Swal.fire({
        icon: 'success',
        title: 'Correcto',
        text: 'El viaje ha sido creado correctamente',
        confirmButtonText: 'Aceptar'    

      });
    }
    console.log(response);
  } catch (err) {
    console.log(err);
    Swal.fire({
      icon: 'error',
      title: '¡Error!',
      text: 'Network Error',
      confirmButtonText: 'Aceptar'    
    });
  }
};

const retrieve = async (id: number) => {
    return http.get<ITravelData>(`/travels/${id}`);
};

const update = async (data: ITravelData) => {
  try {    
    const response = await http.put<ITravelData>(`/travels/${data.id}`, data);
    if(response.status === 200){
      Swal.fire({
        icon: 'success',
        title: 'Correcto',
        text: 'El travel ha sido actualizado',
        confirmButtonText: 'Aceptar'    
      });
    }

  } catch (error) {
    Swal.fire({
      icon: 'error',
      title: '¡Error!',
      text: 'Network Error',
      confirmButtonText: 'Aceptar'    
    });
  }
    
};

const remove = async (id: number) => {
    try {
      const response = await  http.delete<string>(`/travels/${id}`);
      if(response.status === 200){
        Swal.fire({
          icon: 'success',
          title: 'Correcto',
          text: 'El travel ha sido eliminado',
          confirmButtonText: 'Aceptar'    
        });
      }
    } catch (error) {
      Swal.fire({
      icon: 'error',
      title: '¡Error!',
      text: 'Network Error',
      confirmButtonText: 'Aceptar'    
    });
    }

};


const list = (page: number, size: number, sort? : String) => {
  const urlRequest : string = "/travels/" + page + "/" + size ;
  console.log(urlRequest);
  return http.get<Array<ITravelData>>(urlRequest);
};

const count = async () =>  {  
  const response = await http.get<number>("/travels/count");
  return response.data;
};

const TravelService = {
  create,
  retrieve,
  update,
  remove,
  list,
  count

};
export default TravelService;