"use server"

import { FormDataSignUp,env } from "@/utils/types"

export const createUser = async (data:FormDataSignUp) => {
    try{
       const request = await fetch(`${env.API_URL}/auth/register`,{
        method:"POST",
        headers: {
          "Content-Type": "application/json"
        },
        body:JSON.stringify({
            email:data.email,
            password:data.password,
            username:data.username
          })
        })
        
        if(request.status !== 201){
          return { error: 'invalid data' }
        }

        return {success:true}
    }catch{
      return {error: 'Server unavailable'}
    }
}