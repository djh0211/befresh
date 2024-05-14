import { LineChart, Line, XAxis, Label, YAxis, Tooltip, ResponsiveContainer } from 'recharts';
import { informationType } from '../../types/informationTypes';



type dataType = {
  time: string,
  '온도': number,
  '습도': number,
  nh3: number
}
export default function DetailChart({information} :{information:informationType}) {
  const data:dataType[] = []
  const unit:number = Math.floor(information.sensorDataList.humidity.length / 20) + 1
  information.sensorDataList.temperature.map((c, idx) => {
    if (idx % unit == 0) {
      let tempDate = new Date(c.time)
      tempDate.setHours(tempDate.getHours()-9)
      let temp:dataType = {
        time: `${tempDate.getMonth() + 1}/${tempDate.getDate()} ${tempDate.getHours()}시 ${tempDate.getMinutes()}분`,
        '온도': Math.round(c.value * 10) / 10,
        '습도': Math.round(information.sensorDataList.humidity[idx].value * 10) / 10,
        nh3: Math.round(information.sensorDataList.nh3[idx].value * 10) / 10
      }
      data.push(temp)
    }
    
  })

  return (
    <div style={{ width: '90%', height: '80%', margin:'10% 5% 10% 0%' }}>
        <ResponsiveContainer>
          <LineChart
            data={data}
          >
            <XAxis dataKey="time"/>
            <YAxis />
            <Tooltip />
            <Line type="monotone" dataKey="온도" stroke="#8884d8" fill="#8884d8" />
            <Line type="monotone" dataKey="습도" stroke="#82ca9d" fill="#82ca9d" />
            <Line type="monotone" dataKey="nh3" stroke="#ffc658" fill="#ffc658" />
          </LineChart>
        </ResponsiveContainer>
      </div>
  )
}