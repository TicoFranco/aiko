"use client"

import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import L from "leaflet";
import "leaflet/dist/leaflet.css";

const defaultIcon = L.icon({
  iconUrl: "https://unpkg.com/leaflet@1.9.4/dist/images/marker-icon.png",
  shadowUrl: "https://unpkg.com/leaflet@1.9.4/dist/images/marker-shadow.png",
  iconSize: [25, 41],
  iconAnchor: [12, 41],
});

interface MapProps {
  lat: number;
  lon: number;
  name: string;
}

export default function Map({lat,lon,name}:MapProps){
    return(
        <div className="w-50 h-50 mx-auto sm:w-100 h-100">
            <MapContainer center={[lat,lon]} zoom={4} className="w-full h-full">
                <TileLayer attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                   url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />
                <Marker position={[lat,lon]} icon={defaultIcon}>
                    <Popup>
                        <strong>{name}</strong> <br /> Location.
                    </Popup>
                </Marker>
            </MapContainer>
        </div>
    )
}