"use client"

import { useEffect, useState } from "react"
import EquipmentPositionHistory from "./EquipmentPositionHistory"
import { useUserContextApi } from "@/contexts/UserContext"
import { EquipmentPositionHistoryProps } from "@/utils/types"

export default function EquipmentsPositionHistory() {
  const {user} = useUserContextApi()
  const [equipmentPositionHistoryList,setEquipmentPositionHistoryList] = useState<EquipmentPositionHistoryProps[]>([])

  useEffect(() => {
    if(user.equipmentList.length > 0){
      setEquipmentPositionHistoryList(user.equipmentList.map(
        e => ({
          equipmentName:e.name,
          equipmentLicense:e.license,
          equipmentPositionHistory:e.equipment_position_history_list.map(
            eph => ({
              id:eph.id,
              lon:eph.lon,
              lat:eph.lat,
              date:eph.localDateTime
            })
          )
        })
      ))
    }
  },[user])

  return (
    <div className="my-5 mx-auto max-w-xl">
        <h1 className="text-neutral text-5xl my-10">Equipments Position History</h1>
        <ul className="list">
          {equipmentPositionHistoryList.map(eph => <EquipmentPositionHistory key={eph.equipmentLicense} equipmentName={eph.equipmentName} equipmentPositionHistory={eph.equipmentPositionHistory} equipmentLicense=""/>)}
        </ul>
    </div>
  )
}
