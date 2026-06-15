"use client"

import Equipment from "./Equipment"
import { useEffect, useState } from "react"
import AddEquipmentModal from "./AddEquipmentModal"
import DeleteEquipmentModal from "./DeleteEquipmentModal"
import { useUserContextApi } from "@/contexts/UserContext"
import { useProfitsContextApi } from "@/contexts/ProfitsContext"
import { EquipmentDeleteItem,AddEquipmentResult,DeleteEquipmentResult } from "@/utils/types"
import SuccessModal from "../SucessModal"
import ErrorModal from "../ErrorModal"
import { getUserInfo } from "@/app/actions/getUserInfo"

type equipmentSessionType = {
  equipmentModel:string,
  equipmentState:string,
  equipmentName:string,
  equipmentLicense:string
}

export default function Equipments() {
  const [addEquipment,setAddEquipment] = useState(false)
  const [deleteEquipment,setDeleteEquipment] = useState(false)

  const [openSuccessModal,setOpenSuccessModal] = useState(false)
  const [openErrorModal,setOpenErrorModal] = useState(false)

  const [addEquipmentResult,setAddEquipmentResult] = useState<AddEquipmentResult>({state:null,message:'',equipment:null})
  const [deleteEquipmentResult,setDeleteEquipmentResult] = useState<DeleteEquipmentResult>({state:null,message:'',equipment:null})
  const [resultMessage,setResultMessage] = useState('')

  const {user,setUser} = useUserContextApi()
  const {setEarnings} = useProfitsContextApi()

  const [equipments,setEquipments] = useState<equipmentSessionType[]>([])
  const [deleteEquipmentList,setDeleteEquipmentList] = useState<EquipmentDeleteItem[]>([])

  const refreshUser = async () => {
    const newUser = await getUserInfo(user.email)
    if(newUser.sucess){
      setUser(newUser.user)
      setEarnings(newUser.profitsList)
    }
  }

  useEffect(() => {
    if(user.equipmentList.length > 0){
      setEquipments(user.equipmentList.map(
        e => ({
          equipmentModel:e.equipment_model.name,
          equipmentState:e.equipment_state_history_list.at(-1)?.equipmentState.name ?? '',
          equipmentName:e.name,
          equipmentLicense:e.license
        })
      ))
      setDeleteEquipmentList(user.equipmentList.map(
        e => ({name:e.name,license:e.license})
      ))
    }
  },[user])

  useEffect(() => {
    if(addEquipmentResult.state === null){
      return 
    }
    setResultMessage(addEquipmentResult.message)
    if(addEquipmentResult.state === 'success'){
      setOpenSuccessModal(true)
      refreshUser()
    }
    if(addEquipmentResult.state === 'error'){
      setOpenErrorModal(true)
    }
  },[addEquipmentResult])

  useEffect(() => {
    if(deleteEquipmentResult.state === null){
      return
    }
    setResultMessage(deleteEquipmentResult.message)
    if(deleteEquipmentResult.state === 'success'){
      setOpenSuccessModal(true)
      refreshUser()
    }
    if(deleteEquipmentResult.state === 'error'){
      setOpenErrorModal(true)
    }
  },[deleteEquipmentResult])

  useEffect(() => {
    if(!openErrorModal && !openSuccessModal){
      setResultMessage('')
    }
  },[openErrorModal,openSuccessModal])

  return (
    <div className="my-5 mx-auto max-w-xl">
        <h1 className="text-neutral text-5xl my-10">My Equipments</h1>
        <ul className="list">
          {equipments.map(e => <Equipment key={e.equipmentLicense} name={e.equipmentName} model={e.equipmentModel} state={e.equipmentState}/>)}
        </ul>
        <div className="flex flex-col max-w-3xs mx-auto">
            <button className="btn btn-success my-2" onClick={() => setAddEquipment(true)}>Add Equipment</button>
            <button className="btn btn-error" onClick={() => setDeleteEquipment(true)}>Delete Equipment</button>
        </div>
        {addEquipment === true ? <AddEquipmentModal email={user.email} closeModal={() => setAddEquipment(false)} setResult={setAddEquipmentResult}/> : null}
        {deleteEquipment === true ? <DeleteEquipmentModal email={user.email} closeModal={() => setDeleteEquipment(false)} equipmentList={deleteEquipmentList} setResult={setDeleteEquipmentResult}/> : null}
        {openSuccessModal === true ? <SuccessModal message={resultMessage} closeModal={() => setOpenSuccessModal(false)} /> : null}
        {openErrorModal === true ? <ErrorModal message={resultMessage} closeModal={() => setOpenErrorModal(false)} /> : null}
    </div>
  )
}
