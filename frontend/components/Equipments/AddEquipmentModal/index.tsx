"use client"

import { AddEquipmentModalProps,FormDataLicense } from "@/utils/types"
import { useForm, SubmitHandler } from "react-hook-form"
import { addEquipment } from "@/app/actions/addEquipment"
import { useEffect } from "react"

export default function AddEquipmentModal({email,closeModal,setResult}:AddEquipmentModalProps) {
  const {register,handleSubmit,setValue} = useForm<FormDataLicense>()
  const onSubmit: SubmitHandler<FormDataLicense> = async (data) => {
    const res = await addEquipment(data)
    if(res.success){ 
      setResult({state:'success',message:'activated license',equipment:res.equipment})
    }else if (res.error){
      setResult({state:'error',message:`${res.error.message}`,equipment:null})
    }
    closeModal()
  }

  useEffect(() => {
    setValue("email",email)
  },[])

  return (
    <dialog open id="add_equipment_modal" className="modal">
        <div className="modal-box p-10 mb-30">
            <form method="dialog">
                <button onClick={closeModal} className="btn btn-sm btn-circle btn-ghost absolute right-2 top-2">✕</button>
            </form>
            <form onSubmit={handleSubmit(onSubmit)} className="flex flex-col justify-center items-center gap-y-4">
                <h1 className="font-bold text-4xl">License</h1>
                <input type="text" placeholder="Type here" className="input" {...register("license")}/>
                <button type="submit" className="btn btn-success btn-xl">add</button>
            </form>
        </div>
    </dialog>
  )
}