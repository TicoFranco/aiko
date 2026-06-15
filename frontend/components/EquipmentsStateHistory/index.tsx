"use client"

import { useEffect, useState } from "react"
import EquipmentStateHistory from "./EquipmentStateHistory"
import { useUserContextApi } from "@/contexts/UserContext"
import { EquipmentStateHistoryProps } from "@/utils/types"

export default function EquipmentsStateHistory() {
  const {user} = useUserContextApi()
  const [equipmentStateHistoryList,setEquipmentStateHistoryList] = useState<EquipmentStateHistoryProps[]>([])

  useEffect(() => {
    if(user.equipmentList.length > 0){
      setEquipmentStateHistoryList(user.equipmentList.map(
        e => ({
          equipmentName:e.name,
          equipmentLicense:e.license,
          equipmentStateHistory:e.equipment_state_history_list.map(
            esh => ({
              id:esh.id,
              state:esh.equipmentState.name,
              date:esh.localDateTime
            })
          )
        })
      ))
    }
  },[user])

  return (
    <div className="my-5 mx-auto max-w-xl">
        <h1 className="text-neutral text-5xl my-10">Equipments state history</h1>
        <ul className="list">
          {equipmentStateHistoryList.map(esh => <EquipmentStateHistory key={esh.equipmentLicense} equipmentName={esh.equipmentName} equipmentStateHistory={esh.equipmentStateHistory} equipmentLicense=""/>)}
        </ul>
    </div>
  )
}
