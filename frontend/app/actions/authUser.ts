"use server"

import { FormDataLogin,env } from "@/utils/types"
import { cookies } from "next/headers"

export const authUser = async (data:FormDataLogin) => {
    try{
      const request = await fetch(`${env.API_URL}/auth/login`,{
          method:"POST",
          headers: {
            "Content-Type": "application/json"
          },
          body:JSON.stringify({
              email:data.email,
              password:data.password
          })
      })

      const response = await request.json()

      if(!request.ok){
        return { error: 'email or password is incorrect' }
      }

      (await cookies()).set("token",response.token,{httpOnly: true,path: "/"})

      return {success:true}
    }catch{
      return {error: 'Server unavailable'}
    }
}
