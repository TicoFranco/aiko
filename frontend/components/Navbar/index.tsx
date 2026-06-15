"use client"

import { useSessionContextApi } from "@/contexts/SessionContext"
import { useUserContextApi } from "@/contexts/UserContext"

export default function Navbar() {
  const {setSession} = useSessionContextApi()
  const {user,setUser} = useUserContextApi()

  const endSession = () => {
    setUser({email:"",equipmentList:[],username:""})
  }

  return (
    <div className="navbar bg-info-content text-base-100 shadow-sm">
        <input id="my-drawer-1" type="checkbox" className="drawer-toggle" />
        <div className="drawer-content flex-none">
            <label htmlFor="my-drawer-1" className="btn btn-square btn-ghost drawer-button">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" className="inline-block h-6 w-6 stroke-current"> <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 12h16M4 18h16"></path> </svg>
            </label>
        </div>
        <div className="flex-1">
            <a className="btn btn-ghost text-3xl">aiko</a>
        </div>
        <div className="drawer-side">
            <label htmlFor="my-drawer-1" aria-label="close sidebar" className="drawer-overlay"></label>
            <div className="menu bg-info-content min-h-full w-80">

                <label htmlFor="my-drawer-1" className="btn btn-ghost btn-square p-0 w-10 h-10 min-h-0">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="size-10">
                      <path strokeLinecap="round" strokeLinejoin="round" d="M6 18 18 6M6 6l12 12" />
                   </svg>
                </label>

                <div className="w-24 rounded-full mx-20">
                  <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="size-30">
                    <path strokeLinecap="round" strokeLinejoin="round" d="M17.982 18.725A7.488 7.488 0 0 0 12 15.75a7.488 7.488 0 0 0-5.982 2.975m11.963 0a9 9 0 1 0-11.963 0m11.963 0A8.966 8.966 0 0 1 12 21a8.966 8.966 0 0 1-5.982-2.275M15 9.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
                  </svg>
                </div>

                <h1 className="text-3xl mx-21">{user.username}</h1>
                <button className="btn btn-ghost btn-error w-30 my-2 mx-20" onClick={() => endSession()}>End Session</button>

                <ul>
                    <li><label htmlFor="my-drawer-1" className="btn btn-ghost justify-start pl-1" onClick={() =>{setSession("Equipments")}}>Equipments</label></li>
                    <li><label htmlFor="my-drawer-1" className="btn btn-ghost justify-start pl-1" onClick={() =>{setSession("Equipments state history")}}>Equipments state history</label></li>
                    <li><label htmlFor="my-drawer-1" className="btn btn-ghost justify-start pl-1" onClick={() =>{setSession("Equipments position history")}}>Equipments position history</label></li>
                    <li><label htmlFor="my-drawer-1" className="btn btn-ghost justify-start pl-1" onClick={() =>{setSession("Earnings")}}>Earnings</label></li>
                </ul>

            </div>
        </div>
    </div>
  )
}