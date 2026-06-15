"use client"

import { createContext,useState,useContext } from "react"
import { profitsTimeline } from "@/utils/types"

type ProfitsContextTpe = {
    earnings:profitsTimeline[],
    setEarnings: (profitsList:profitsTimeline[]) => void
}

const ProfitsContext = createContext<ProfitsContextTpe>({earnings:[],setEarnings:() => {}})

export const ProfitsProvider = ({children}: {children:React.ReactNode}) => {
    const [earnings,setEarnings] = useState<profitsTimeline[]>([])

    return(
        <ProfitsContext.Provider value={{earnings,setEarnings}}>
            {children}
        </ProfitsContext.Provider>
    )
}

export const useProfitsContextApi = () => {
    const context = useContext(ProfitsContext)
    return context
}