"use client"

import { EquipmentPositionHistoryProps } from "@/utils/types"
import dynamic from "next/dynamic";

const MapArtifact = dynamic(() => import("@/components/Map"),{
    ssr:false,
    loading: () => <p>Loading map..</p>
})

export default function EquipmentPositionHistory({equipmentName,equipmentPositionHistory,equipmentLicense}:EquipmentPositionHistoryProps) {
  return (
    <li className="list-row">
        <div className="collapse bg-base-100 hover:bg-base-300 transition-all border border-base-300 font-semibold max-w-xl w-full">
            <input id={`collapse-${equipmentName}-toggle`} type="checkbox" className="peer"/>
            <label htmlFor={`collapse-${equipmentName}-toggle`} className="fixed inset-0 hidden peer-checked:block"></label>
            <div className="collapse-title mr-100">
               <h1 className="text-xl">{equipmentName}</h1>
            </div>
            <ul className="list flex flex-col collapse-content">
                {equipmentPositionHistory.map(eph => 
                <li key={eph.id} className="list-row flex flex-col">
                    <div className="flex flex-row gap-x-4">
                        <h1>Lon: {eph.lon}</h1>
                        <h1>Lat: {eph.lat}</h1>
                        <h1>Date: {eph.date}</h1>
                    </div>
                    <MapArtifact lat={eph.lat} lon={eph.lon} name={equipmentName}/>
                </li>)}
            </ul>
        </div>
    </li>
  )
}
