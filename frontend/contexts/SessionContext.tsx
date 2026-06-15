"use client"

import { createContext,useState,useContext } from "react"

type SessionContextType = {
    session:string
    setSession: (session:string) => void
}

const SessionContext = createContext<SessionContextType>({session:"Equipments",setSession:() => {}})

export const SessionProvider = ({children}: {children:React.ReactNode}) => {
    const [session,setSession] = useState<string>("Equipments")

    return(
        <SessionContext.Provider value={{session,setSession}}>
            {children}
        </SessionContext.Provider>
    )
}

export const useSessionContextApi = () =>{
    const context = useContext(SessionContext)
    return context
}