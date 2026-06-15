"use server"

import { env,FormDataLicense,IEquipment } from "@/utils/types"
import { cookies } from "next/headers"

export const addEquipment = async (data:FormDataLicense) => {
    try{
        const request = await fetch(`${env.API_URL}/user/license`,{
            method:"POST",
            headers:{
                "Authorization":`Bearer ${(await cookies()).get('token')?.value}`,
                "Content-Type": "application/json"
            },
            body:JSON.stringify({
                email:data.email,
                license:data.license
            })
        })

        const response = await request.json()

        if(!request.ok){
            return { error: response.message }
        }

        const equipment:IEquipment = response

        equipment.equipment_position_history_list.forEach(eph => eph.localDateTime = new Date(eph.localDateTime).toLocaleString("en-US",{dateStyle:"medium",timeStyle:"short"}))
        equipment.equipment_state_history_list.forEach(esh => esh.localDateTime = new Date(esh.localDateTime).toLocaleString("en-US",{dateStyle:"medium",timeStyle:"short"}))

        return {success:true,equipment}
    }catch{
        return {error: 'Server unavailable'}
    }
}