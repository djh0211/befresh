import { latestInformationType } from "../../types/informationTypes"

export default function SensorData({latestInformation}: {latestInformation:latestInformationType}) {
  return (
    <>
      <p>{latestInformation.sensorDataList.temperature}</p>
    </>
  )
}