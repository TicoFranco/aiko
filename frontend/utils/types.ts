export const env = {
    API_URL:process.env.API_URL
}

//Success and Error Modal

export interface ModalProps{
    message:string,
    closeModal:() => void
}


//Input Data


export interface FormDataLogin{
    email:string,
    password:string
}

export interface FormDataSignUp{
    username:string,
    email:string,
    password:string
}

export interface FormDataLicense{
    email:string,
    license:string
}


//DeleteEquipmentModal


export interface DeleteEquipmentModalProps{
    email:string
    equipmentList:EquipmentDeleteItem[],
    closeModal:() => void,
    setResult:(result:DeleteEquipmentResult) => void
}

export type EquipmentDeleteItem = {
    name:string,
    license:string
}

export type DeleteEquipmentResult = {
    state:string | null,
    message:string,
    equipment:EquipmentDeleteItem | null
}


//AddEquipmentModal


export interface AddEquipmentModalProps{
    email:string,
    closeModal:() => void,
    setResult:(result:AddEquipmentResult) => void
}

export type AddEquipmentResult = {
    state:string | null,
    message:string,
    equipment:IEquipment | null
}


//Equipment State History


export interface EquipmentStateHistoryProps{
    equipmentName:string,
    equipmentLicense:string,
    equipmentStateHistory:EquipmentStateProps[]
}

export interface EquipmentStateProps{
    id:string,
    state:string,
    date:string
}

export interface IEquipmentStateHistory{
    id:string,
    equipmentState:{id:string,name:string,color:string},
    localDateTime:string
}


//Equipment Position History


export interface EquipmentPositionHistoryProps{
    equipmentName:string,
    equipmentLicense:string
    equipmentPositionHistory:EquipmentPosition[]
}

export type EquipmentPosition = {
    id:string,
    lon:number,
    lat:number,
    date:string
}

export interface IEquipmentPositionHistory{
    id:string,
    localDateTime:string,
    lat:number,
    lon:number
}


//Equipment

export interface IEquipment{
    Owner:{id:string,email:string,username:string},
    equipment_model:{id:string,name:string},
    equipment_position_history_list:IEquipmentPositionHistory[],
    equipment_state_history_list:IEquipmentStateHistory[],
    id:string,
    license:string,
    name:string
}

export interface IUser{
    email:string,
    equipmentList:IEquipment[],
    username:string
}

export type equipmentProfit = {
    id:string,
    name:string,
    license:string
}

//Earnings

export type profit = {
    date:string,
    value:number
}

export interface profitsTimeline{
    equipment:equipmentProfit,
    profits:profit[]
}