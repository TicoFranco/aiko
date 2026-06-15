"use client"

import { useSessionContextApi } from "@/contexts/SessionContext";
import { useUserContextApi } from "@/contexts/UserContext";
import { deleteCookies } from "../actions/deleteCookies";
import { useRouter } from "next/navigation"
import { useEffect } from "react";

export default function Home(){
  const {setSession} = useSessionContextApi()
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
    }else{
      router.push('/dashboard')
    }
  },[])

  return (
    <div>
    </div>
  );
}
