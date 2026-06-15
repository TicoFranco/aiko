"use client"

import { EquipmentStateHistoryProps } from "@/utils/types"

export default function EquipmentStateHistory({equipmentName,equipmentStateHistory,equipmentLicense}:EquipmentStateHistoryProps) {

  function setColor(state:string){
    switch(state){
        case "working":
            return "success"
        case "static":
            return "warning"
        case "defect":
            return "error"
        default:
            return "neutral"
    }
  } 
    
  return (
    <li className="list-row">
        <div className="collapse bg-base-100 hover:bg-base-300 transition-all border border-base-300 font-semibold max-w-xl w-full">
            <input id={`collapse-${equipmentName}-toggle`} type="checkbox" className="peer"/>
            <label htmlFor={`collapse-${equipmentName}-toggle`} className="fixed inset-0 hidden peer-checked:block"></label>
            <div className="collapse-title mr-100">
               <h1 className="text-xl">{equipmentName}</h1>
            </div>
            <ul className="list flex flex-col collapse-content">
                {equipmentStateHistory.map(esh => 
                <li key={esh.id} className="list-row flex flex-row gap-x-4">
                    <h1>State: <span className={`text-${setColor(esh.state)}`}>{esh.state}</span></h1>
                    <h1>Date: {esh.date}</h1>
                </li>)}
            </ul>
        </div>
    </li>
  )
}