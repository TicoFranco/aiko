"use client"

import Navbar from "@/components/Navbar";
import Equipments from "@/components/Equipments";
import EquipmentsStateHistory from "@/components/EquipmentsStateHistory";
import EquipmentsPositionHistory from "@/components/EquipmentsPositionHistory";
import Earnings from "@/components/Earnings";
import { useSessionContextApi } from "@/contexts/SessionContext";
import { useUserContextApi } from "@/contexts/UserContext";
import { deleteCookies } from "@/app/actions/deleteCookies";
import { useRouter } from "next/navigation"
import { useEffect } from "react";

export default function Dashboard(){
  const {session,setSession} = useSessionContextApi()
  const {user} = useUserContextApi()
  const router = useRouter()

  const endSession = async () => {
    const searchCookies = await deleteCookies()
    if(searchCookies){
      setSession("Equipments")
      router.replace('/login')
    }
  }

  useEffect(() => {
    if(!user.email && !user.username && user.equipmentList.length === 0){
      endSession()
    }
  },[user])

  return (
    <div>
      <Navbar />
      <div>
        {session === "Equipments" ? (<Equipments />) : 
        session === "Equipments state history" ? (<EquipmentsStateHistory />) : 
        session === "Equipments position history" ? (<EquipmentsPositionHistory />) :
        <Earnings />
        }
      </div>
    </div>
  );
}