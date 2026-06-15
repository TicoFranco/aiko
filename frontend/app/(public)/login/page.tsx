"use client"

import { FormDataLogin } from "@/utils/types"
import { useForm, SubmitHandler } from "react-hook-form"
import ErrorModal from "@/components/ErrorModal"
import Link from "next/link"
import { useState } from "react"
import { authUser } from "@/app/actions/authUser"
import { useRouter } from "next/navigation"
import { getUserInfo } from "@/app/actions/getUserInfo"
import { useUserContextApi } from "@/contexts/UserContext"
import { useProfitsContextApi } from "@/contexts/ProfitsContext"

export default function login() {
  const router = useRouter()
  const {setUser} = useUserContextApi()
  const {setEarnings} = useProfitsContextApi()
  const {register,handleSubmit,formState: { errors }} = useForm<FormDataLogin>({mode:"onChange"})
  const [openErrorModal,setOpenErrorModal] = useState(false)
  const [errorMessage,setErrorMessage] = useState('')
  const onSubmit: SubmitHandler<FormDataLogin> = async (data) => {
    const authResult = await authUser(data)
    if(authResult.error){
        setErrorMessage(authResult.error)
        setOpenErrorModal(true)
    }else if(authResult.success){
        const userResult = await getUserInfo(data.email)
        if(userResult.sucess){
            setUser(userResult.user)
            setEarnings(userResult.profitsList)
            router.refresh()
            router.push('/dashboard')
        }else{
            setErrorMessage(userResult.error)
            setOpenErrorModal(true)
        }
    }
  }

  return (
    <div>
        <div className="navbar bg-info-content shadow-sm text-base-100">
           <a className="btn btn-ghost text-3xl">aiko</a>
        </div>
        <div className="my-5 mx-auto max-w-xl">
            <h1 className="text-neutral text-5xl my-10 ml-47">Login</h1>
            <div>
                <form onSubmit={handleSubmit(onSubmit)} className="flex flex-col ml-25">
                    <div>

                        <label className="input validator my-2">
                          <svg className="h-[1em] opacity-50" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                              <g strokeLinejoin="round" strokeLinecap="round" strokeWidth="2.5" fill="none" stroke="currentColor">
                                  <rect width="20" height="16" x="2" y="4" rx="2"></rect>
                                  <path d="m22 7-8.97 5.7a1.94 1.94 0 0 1-2.06 0L2 7"></path>
                              </g>
                           </svg>
                           <input type="email" placeholder="mail@site.com" className={`input input-info-content input-lg focus:outline-none focus:ring-0 ${errors.email ? "input-error border-error" : "focus:border-gray-300"}`} 
                            {...register("email",{required:"Email is required",pattern:{value:/^[^\s@]+@[^\s@]+\.[^\s@]+$/,message:"Invalid email address"}})}/>
                        </label>
                        {errors.email && (<p className="text-error text-sm mt-1">{errors.email.message}</p>)}

                    </div>
                    <div>

                        <label className="input validator my-1">
                          <svg className="h-[1em] opacity-50" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                             <g strokeLinejoin="round" strokeLinecap="round" strokeWidth="2.5" fill="none" stroke="currentColor">
                                <path d="M2.586 17.414A2 2 0 0 0 2 18.828V21a1 1 0 0 0 1 1h3a1 1 0 0 0 1-1v-1a1 1 0 0 1 1-1h1a1 1 0 0 0 1-1v-1a1 1 0 0 1 1-1h.172a2 
                                 2 0 0 0 1.414-.586l.814-.814a6.5 6.5 0 1 0-4-4z"></path>
                                <circle cx="16.5" cy="7.5" r=".5" fill="currentColor"></circle>
                              </g>
                          </svg>
                          <input type="password" placeholder="Password" className={`input input-info-content input-lg focus:outline-none focus:ring-0 ${errors.password ? "input-error border-error" : "focus:border-gray-300"}`} 
                           {...register("password", {required: "Password is required",minLength: {value: 4,message: "Minimum 4 characters"},
                           pattern: {value: /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{4,}$/,message:"Must contain uppercase, lowercase and number"}})}/>
                        </label>
                        {errors.password && (<p className="text-error text-sm mt-1">{errors.password.message}</p>)}

                    </div>
                    <button type="submit" className="btn btn-info mt-2 max-w-50 ml-15 text-base-100">Login</button>
                </form>
                <p className="mt-4 ml-36">Don't have an account? <Link href={"/signup"} className="text-success">SignUp</Link></p>
            </div>
        </div>
        {openErrorModal === true ? <ErrorModal message ={errorMessage} closeModal={() => setOpenErrorModal(false)}/> : null}
    </div>
  )
}
