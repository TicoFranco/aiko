"use client"

type EquipmentData = {
    name:string
    model:string
    state:string
}

export default function Equipment({name,model,state}:EquipmentData) {
  
  function setColor(state:string){
    switch(state){
        case "working":
            return "success"
        case "static":
            return "warning"
        case "defect":
            return "error"
        default:
            return "neutral"
    }
  } 
 
  return (
    <li className="list-row">
        <div className="collapse bg-base-100 hover:bg-base-300 transition-all border border-base-300 font-semibold max-w-xl w-full">
            <input id={`collapse-${name}-toggle`} type="checkbox" className="peer"/>
            <label htmlFor={`collapse-${name}-toggle`} className="fixed inset-0 hidden peer-checked:block"></label>
            <div className="collapse-title mr-100">
               <h1 className="text-xl">{name}</h1>
            </div>
            <div className="flex flex-row collapse-content text-sm justify-between w-70">
              <h1>Model: {model}</h1>
              <h1>Current State: <span className={`text-${setColor(state)}`} >{state}</span></h1>
            </div>
        </div>
    </li> 
  )
}
