"use client"

import { useEffect } from "react"
import EquipmentEarnings from "./EquipmentEarnings"
import { useProfitsContextApi } from "@/contexts/ProfitsContext"

export default function Earnings() {
  const {earnings} = useProfitsContextApi()

  useEffect(() => {
    console.log(earnings)
  },[])
  
  return (
    <div className="my-5 mx-auto max-w-xl">
        <h1 className="text-neutral text-5xl my-10">Earnings</h1>
        <ul className="list">
          {earnings.map(e => <EquipmentEarnings key={e.equipment.id} equipment={e.equipment} profits={e.profits}/>)}
        </ul>
    </div>
  )
}
