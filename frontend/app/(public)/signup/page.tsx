"use client"

import { useForm, SubmitHandler } from "react-hook-form"
import { useState } from "react"
import { FormDataSignUp } from "@/utils/types"
import ErrorModal from "@/components/ErrorModal"
import SuccessModal from "@/components/SucessModal"
import { createUser } from "@/app/actions/createUser"
import { useRouter } from "next/navigation"

export default function signup() {
  const router = useRouter()
  const {register,handleSubmit,formState: { errors }} = useForm<FormDataSignUp>({mode:"onChange"})
  const [openSuccessModal,setOpenSuccessModal] = useState(false)
  const [openErrorModal,setOpenErrorModal] = useState(false)
  const [errorMessage,setErrorMessage] = useState('')
  const onSubmit: SubmitHandler<FormDataSignUp> = async (data) => {
    const result = await createUser(data)
    if(result.error){
        setErrorMessage(result.error)
        setOpenErrorModal(true)
    }else if (result.success){
        setOpenSuccessModal(true)
        setTimeout(() => {router.push("/login")},2000)
    }
  }

  return (
    <div>
        <div className="navbar bg-info-content shadow-sm text-base-100">
           <a className="btn btn-ghost text-3xl">aiko</a>
        </div>
        <div className="my-5 mx-auto max-w-xl">
            <h1 className="text-neutral text-5xl my-10 ml-40">Sign Up</h1>
            <form onSubmit={handleSubmit(onSubmit)} className="flex flex-col ml-25">
                <div>

                    <label className="input validator mt-2">
                      <svg className="h-[1em] opacity-50" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                          <g strokeLinejoin="round" strokeLinecap="round" strokeWidth="2.5" fill="none" stroke="currentColor">
                              <path d="M19 21v-2a4 4 0 0 0-4-4H9a4 4 0 0 0-4 4v2"></path>
                              <circle cx="12" cy="7" r="4"></circle>
                          </g>
                      </svg>
                      <input type="text" className={`input input-info-content input-lg focus:outline-none focus:ring-0 ${errors.username ? "input-error border-error" : "focus:border-gray-300"}`} 
                        placeholder="Username" {...register("username",{required:"Username is required",minLength:{value:3,message:"Minimum 3 characters"},
                        maxLength:{value:30,message:"Maximum 30 characters"},pattern:{value:/^[A-Za-z][A-Za-z0-9-]*$/,message:"Only letters, numbers or dash"}})}/>
                    </label>
                    {errors.username && ( <p className="text-error text-sm mt-1">{errors.username.message}</p>)}

                </div>
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
                      <input type="password" className={`input input-info-content input-lg focus:outline-none focus:ring-0 ${errors.password ? "input-error border-error" : "focus:border-gray-300"}`} 
                         placeholder="Password" {...register("password", {required: "Password is required",minLength: {value: 4,message: "Minimum 4 characters"},
                         pattern: {value: /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{4,}$/,message:"Must contain uppercase, lowercase and number"}})}/>
                    </label>
                    {errors.password && (<p className="text-error text-sm mt-1">{errors.password.message}</p>)}

                </div>
                <button type="submit" className="btn btn-info mt-2 max-w-50 ml-15 text-base-100">Sign up</button>
            </form>
        </div>
        {openSuccessModal === true ? <SuccessModal message="account created" closeModal={() => setOpenSuccessModal(false)}/> : null}
        {openErrorModal === true ? <ErrorModal message={errorMessage} closeModal={() => setOpenErrorModal(false)}/> : null}
    </div>
  )
}
