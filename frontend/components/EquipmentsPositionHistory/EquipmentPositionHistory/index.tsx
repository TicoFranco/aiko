"use client"

import { EquipmentPositionHistoryProps } from "@/utils/types"

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
                    <h1 className="mt-2">search location: <a className="link link-info link-hover"
                        href={`https://www.google.com/maps?q=${eph.lat},${eph.lon}`}>Map</a></h1>
                </li>)}
            </ul>
        </div>
    </li>
  )
}
