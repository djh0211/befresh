import { LineChart, Line, XAxis, YAxis, Tooltip, ResponsiveContainer } from 'recharts';
import { informationType } from '../../types/informationTypes';
import { useState } from 'react';
import { ToggleButton, ToggleButtonGroup } from '@mui/material';
import styled from 'styled-components';

const ChartBox = styled.div`
  width: 95%;
  height: 90%;
`
type dataType = {
  time: string,
  '온도': number,
  '습도': number,
  'nh3': number
}

export default function DetailChart({ information }: Readonly<{ information: informationType }>) {
  const data: dataType[] = []
  const unit: number = Math.floor(information.sensorDataList.humidity.length / 20) + 1
  information.sensorDataList.temperature.map((c, idx) => {
    if (idx % unit == 0 || idx === information.sensorDataList.temperature.length - 1) {
      let tempDate = new Date(c.time)
      tempDate.setHours(tempDate.getHours() - 9)
      let temp: dataType = {
        time: `${tempDate.getMonth() + 1}/${tempDate.getDate()} ${tempDate.getHours()}:${tempDate.getMinutes()}`,
        '온도': Math.round(c.value * 10) / 10,
        '습도': Math.round(information.sensorDataList.humidity[idx].value * 10) / 10,
        'nh3': Math.round(information.sensorDataList.nh3[idx].value * 10) / 10
      }
      data.push(temp)
    }
  })

  // 원하는 데이터만 보기
  const [selectedInfos, setSelectedInfos] = useState<('온도'|'습도'|'nh3')[]>(['온도', '습도', 'nh3'])
  const handleChangeInfo = (_event: React.MouseEvent<HTMLElement> | React.TouchEvent<HTMLElement>, newInfo:('온도'|'습도'|'nh3')[]) => {
    setSelectedInfos(newInfo)
  }
  const colors:{'온도': string, '습도': string, 'nh3': string} = {
    '온도' : "#8884d8",
    '습도': "#82ca9d",
    'nh3': "#ffc658"
  }

  return (
    <ChartBox>
      <ToggleButtonGroup
        value={selectedInfos}
        onChange={handleChangeInfo}
        size='small'
        sx={{height:'2.5vh', marginLeft:'37%', marginTop:'3%'}}
      >
        <ToggleButton value={'온도'} sx={{fontSize: '1.2rem'}}>온도</ToggleButton>
        <ToggleButton value={'습도'} sx={{fontSize: '1.2rem'}}>습도</ToggleButton>
        <ToggleButton value={'nh3'} sx={{fontSize: '1.2rem'}}>NH3</ToggleButton>
      </ToggleButtonGroup>
      {
        selectedInfos.length === 0 ? (<p>확인하고 싶은 정보를 선택해주세요.</p>) : (
          <div style={{ width:'100%', height:'80%', margin: '10% 5% 10% 0%' }}>
            <ResponsiveContainer>
              <LineChart
                data={data}
              >
                <XAxis dataKey="time" />
                <YAxis />
                <Tooltip />
                {
                  selectedInfos.map((info, idx) => {
                    return (
                      <Line type="monotone" dataKey={info} stroke={colors[info]} fill={colors[info]} key={idx}/>
                    )
                  })
                }
              </LineChart>
            </ResponsiveContainer>
          </div>
        )
      }
      
    </ChartBox>
  )
}