"use server"

import { cookies } from "next/headers"
import { IUser,env, profitsTimeline } from "@/utils/types"

export const getUserInfo = async (email:string) => {
    try{
        const emailEncoded = encodeURIComponent(email)
        const [userRequest,equipmentEarningsRequest] = await Promise.all([
          fetch(`${env.API_URL}/user/${emailEncoded}`,{
            method:"GET",
            headers:{
              "Authorization":`Bearer ${(await cookies()).get('token')?.value}`
            }
          }),
          fetch(`${env.API_URL}/user/profits/${emailEncoded}`,{
            method:"GET",
            headers:{
              "Authorization":`Bearer ${(await cookies()).get('token')?.value}`
            }
          })
        ])

        const [userResponse,equipmentEarningsResponse] = await Promise.all([userRequest.json(),equipmentEarningsRequest.json()])
        
        if(!userRequest.ok){
          return { error: userResponse.message }
        }

        if(!equipmentEarningsRequest.ok){
          return {error:equipmentEarningsResponse.message}
        }

        const user:IUser = userResponse
        const profitsList:profitsTimeline[] = equipmentEarningsResponse

        user.equipmentList.forEach(e => {
          e.equipment_position_history_list.forEach(eph => eph.localDateTime = new Date(eph.localDateTime).toLocaleString("en-US",{dateStyle:"medium",timeStyle:"short"}))
          e.equipment_state_history_list.forEach(esh => esh.localDateTime = new Date(esh.localDateTime).toLocaleString("en-US",{dateStyle:"medium",timeStyle:"short"}))
        })

        profitsList.forEach(profitsTimeline => {
          profitsTimeline.profits.forEach(profit => {profit.date = new Date(profit.date).toLocaleString("en-US",{dateStyle:"medium"})})
        })

        return {sucess:true,user,profitsList}
    }catch{
        return {error: 'Server unavailable'}
    }
}