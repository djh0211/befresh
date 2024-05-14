import styled from "styled-components";
import Paper from '@mui/material/Paper'
import SensorData from "../molecules/sensorData";
import DetailChart from "../molecules/detailChart";
import { informationType, latestInformationType } from "../../types/informationTypes";

const InfoTitle = styled.p`
  text-align: center;
  font-size: 2.5rem;
  margin: 0;
`
const InfoBoxStyle = {
  width : '90vw',
  height: '30vh',
  backgroundColor: 'rgba(161, 193, 167, 0.2)',
  margin: '4vh auto',
  padding: '25px 0px'
}

const InfoContent = styled.div`
  width: 94%;
  height: 80%;
  display: flex;
  margin: 5% 3%;
  justify-content: space-between;
`

const InfoContentBox = styled.div`
  width: 35%;
  height: 95%;
  background-color: #ffffffFB;
  border-radius: 10px;
  padding: 10px;
`

const ChartBox = styled.div`
  width: 55%;
  height: 95%;
  background-color: #ffffffFB;
  border-radius: 10px;
  padding: 10px;
`
export default function InfoForm({infomation} :{infomation:informationType}) {

  const latestInformation:latestInformationType =  {
    regDttm: infomation.regDttm,
    elapsedTime: infomation.elapsedTime,
    expirationDate: infomation.expirationDate,
    refresh: infomation.refresh,
    sensorDataList: {
      temperature: infomation.sensorDataList.temperature.length != 0 ? infomation.sensorDataList.temperature[infomation.sensorDataList.temperature.length - 1].value : null,
      humidity: infomation.sensorDataList.humidity.length != 0 ? infomation.sensorDataList.humidity[infomation.sensorDataList.humidity.length - 1].value : null,
      nh3: infomation.sensorDataList.nh3.length != 0 ? infomation.sensorDataList.nh3[infomation.sensorDataList.nh3.length - 1].value : null
    }
  }

  return (
    <Paper elevation={4} sx={InfoBoxStyle}>
      <InfoTitle>{infomation.name}</InfoTitle>
      <InfoContent>
        <InfoContentBox>
          <SensorData latestInformation={latestInformation}/>
        </InfoContentBox>
        <ChartBox>
          <DetailChart/>
        </ChartBox>
      </InfoContent>
    </Paper>
  );
}
