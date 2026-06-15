"use server"

import { FormDataLicense,env } from "@/utils/types"
import { cookies } from "next/headers"

export const deleteEquipment = async (data:FormDataLicense) => {
    try{
        const request = await fetch(`${env.API_URL}/user/license`,{
            method:"DELETE",
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
            return {error: response.message}
        }

        return {success:true}

    }catch{
       return {error: 'Server unavailable'}
    }
}