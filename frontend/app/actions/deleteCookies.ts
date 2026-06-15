"use server"

import { cookies } from "next/headers"

export const deleteCookies = async () =>{
    const cookiesStore = await cookies()
    if(!cookiesStore){
        return false
    }
    cookiesStore.delete({name:"token",path:"/"})
    return true
}