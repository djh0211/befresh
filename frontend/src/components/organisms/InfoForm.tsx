import styled from "styled-components";
import Paper from '@mui/material/Paper';
import SensorData from "../molecules/sensorData";
import DetailChart from "../molecules/detailChart";
import { informationType, latestInformationType } from "../../types/informationTypes";

const InfoTitle = styled.p`
  text-align: center;
  font-size: 2.5rem;
  margin: 0;
`;

const InfoBoxStyle = {
  width: '90vw',
  height: 'auto',
  backgroundColor: 'rgba(161, 193, 167, 0.2)',
  margin: '4vh auto',
  padding: '25px 0px'
};

const InfoContent = styled.div`
  width: 94%;
  display: flex;
  margin: 5% 3%;
  justify-content: space-between;
`;

const InfoContentBox = styled.div`
  width: 35%;
  background-color: #ffffffFB;
  border-radius: 10px;
  padding: 10px;
`;

const ChartBox = styled.div`
  position: relative;
  width: 55%;
  background-color: #ffffffFB;
  border-radius: 10px;
  padding: 10px;
`;

const LastMeasurementTime = styled.div`
  position: absolute;
  bottom: 5px;
  right: 5px;
  font-size: 0.8rem;
  color: #555;
`;

const formatDate = (dateString: string): string => {
  const date = new Date(dateString);
  date.setHours(date.getHours() - 9)
  const hours = date.getHours().toString().padStart(2, '0');
  const minutes = date.getMinutes().toString().padStart(2, '0');
  return `${date.toLocaleDateString()} ${hours}:${minutes}`;
};

export default function InfoForm({ information }: Readonly<{ information: informationType }>) {
  const latestInformation: latestInformationType = {
    regDttm: information.regDttm,
    elapsedTime: information.elapsedTime,
    expirationDate: information.expirationDate,
    refresh: information.refresh,
    sensorDataList: {
      temperature: information.sensorDataList.temperature.length != 0 ? information.sensorDataList.temperature[information.sensorDataList.temperature.length - 1].value : null,
      humidity: information.sensorDataList.humidity.length != 0 ? information.sensorDataList.humidity[information.sensorDataList.humidity.length - 1].value : null,
      nh3: information.sensorDataList.nh3.length != 0 ? information.sensorDataList.nh3[information.sensorDataList.nh3.length - 1].value : null
    }
  };
  const lastTime: string | null = information.sensorDataList.temperature.length != 0 ? information.sensorDataList.temperature[information.sensorDataList.temperature.length - 1].time : null

  return (
    <Paper elevation={4} sx={InfoBoxStyle}>
      <InfoTitle>{information.name}</InfoTitle>
      <InfoContent>
        <InfoContentBox>
          <SensorData latestInformation={latestInformation} />
        </InfoContentBox>
        <ChartBox>
          {
            information.sensorDataList.temperature.length === 0 ? (
              <p style={{marginTop: '45%', textAlign: 'center'}}>아직 데이터를 측정하기 전입니다.</p>
            ) : (
              <>
                <DetailChart information={information} />
                <LastMeasurementTime>{lastTime ? `마지막 측정 시간: ${formatDate(lastTime)}` : null}</LastMeasurementTime>
              </>
            )
          }
        </ChartBox>
      </InfoContent>
    </Paper>
  );
}
