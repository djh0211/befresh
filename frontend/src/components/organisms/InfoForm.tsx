import styled from "styled-components";
import Paper from '@mui/material/Paper'
import SensorData from "../molecules/sensorData";

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


export default function InfoForm() {
  return (
    <Paper elevation={4} sx={InfoBoxStyle}>
      <InfoTitle>{'돼지 고기'}</InfoTitle>
      <InfoContent>
        <InfoContentBox>
          <SensorData/>
        </InfoContentBox>
        <ChartBox>
          <p>차트 들어갈 곳</p>
        </ChartBox>
      </InfoContent>
    </Paper>
  );
}
