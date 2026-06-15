"use client"

import { createContext,useState,useContext } from "react"
import { IUser } from "@/utils/types"

type UserContextType = {
    user:IUser,
    setUser:(user:IUser) => void
}

const UserContext = createContext<UserContextType>({user:{email:"",equipmentList:[],username:""},setUser:() => {}})

export const UserProvider = ({children}:{children:React.ReactNode}) => {
    const [user,setUser] = useState<IUser>({email:"",equipmentList:[],username:""})

    return(
        <UserContext.Provider value={{user,setUser}}>
            {children}
        </UserContext.Provider>
    )
}

export const useUserContextApi = () =>{
    const context = useContext(UserContext)
    return context
}