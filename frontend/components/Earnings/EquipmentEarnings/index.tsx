"use client"

import { profitsTimeline } from "@/utils/types"
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  BarElement,
  ArcElement,
  Tooltip,
  Legend,
  Title
} from "chart.js"
import { Line } from "react-chartjs-2";

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  BarElement,
  ArcElement,
  Tooltip,
  Legend,
  Title
)

export default function EquipmentEarnings({equipment,profits}:profitsTimeline) {
  const dates = profits.map(p => p.date)
  const values = profits.map(p => p.value)

  return (
    <li className="list-row">
      <div className="collapse bg-base-100 hover:bg-base-300 transition-all border border-base-300 font-semibold max-w-xl w-full">
        <input id={`collapse-${equipment.name}-toggle`} type="checkbox" className="peer"/>
        <label htmlFor={`collapse-${equipment.name}-toggle`} className="fixed inset-0 hidden peer-checked:block"></label>
        <div className="collapse-title mr-100">
          <h1 className="text-xl">{equipment.name}</h1>
        </div>
        <div className="collapse-content">
          <div className="w-full h-80">
            <Line data={{labels:dates,datasets:[{label:'profits',data:values,backgroundColor:"#228B22",borderColor:"#228B22"}]}} 
              options={{responsive:true,maintainAspectRatio:false,plugins:{title:{display:true,text:`${equipment.name} earnings`}}}}/>
          </div>
        </div>
      </div>
    </li>
  )
}
