"use client"

import { DeleteEquipmentModalProps, EquipmentDeleteItem, FormDataLicense } from "@/utils/types"
import { useForm, SubmitHandler } from "react-hook-form"
import { deleteEquipment } from "@/app/actions/deleteEquipment"

type equipmentSelected = {
    equipmentName:string
}

export default function DeleteEquipmentModal({email,closeModal,equipmentList,setResult}:DeleteEquipmentModalProps) {
    const {register,handleSubmit} = useForm<equipmentSelected>()
    const onSubmit: SubmitHandler<equipmentSelected> = async (data) => {
        const selected = equipmentList.find(e => e.name = data.equipmentName)
        if(selected){
            const param:FormDataLicense = {email:email,license:selected.license}
            const res = await deleteEquipment(param)
            if(res.success){
                setResult({state:'success',message:'license removed',equipment:selected})
            }else if (res.error){
                setResult({state:'error',message:`${res.error.message}`,equipment:null})
            }
        }
    }

    return (
    <dialog open id="delete_equipment_modal" className="modal">
        <div className="modal-box p-10 mb-30">
            <form method="dialog">
                <button onClick={closeModal} className="btn btn-sm btn-circle btn-ghost absolute right-2 top-2">✕</button>
            </form>
            <form onSubmit={handleSubmit(onSubmit)} className="flex flex-col gap-y-3">
                <h1 className="font-bold">Select one equipment</h1>
                <ul className="list">
                    {equipmentList.map(equipment => 
                    <li key={equipment.license} className="list-row flex flex-row">
                        <input type="radio" className="radio mr-3" value={equipment.name} {...register('equipmentName')}/>
                        <p>{equipment.name}</p>
                    </li>
                    )}
                </ul>
                <button type="submit" className="btn btn-error w-30">delete</button>
            </form>
        </div>
    </dialog>
  )
}