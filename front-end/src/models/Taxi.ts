 
export default interface ITaxiModel {
    id?: number | null,
    licensePlate : string,
    model : string,
    tradeMark : string,
    registration : number,
    createdDate : string,
    createdBy : string | null,
    updatedDate : number | null,
    updatedBy : string | null     
}