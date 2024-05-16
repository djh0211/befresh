import { latestInformationType } from "../../types/informationTypes";
import styled from "styled-components";

const Senserstyle = styled.div`
  margin-top: 3vh;
  margin-bottom: 3vh;
`;

const StyledDataItem = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const StyledDataTitle = styled.div`
  width: 100px;
  text-align: center;
`;

const StyledDataValue = styled.p`
  flex: 1;
  text-align: center;
`;

export default function SensorData({
  latestInformation,
}: Readonly<{
  latestInformation: latestInformationType;
}>) {
  const formatTemperature = (temperature: number | null): string => {
    if (temperature === null) return "측정 전";
    return temperature.toFixed(1) + "°C";
  };

  const formatHumidity = (humidity: number | null): string => {
    if (humidity === null) return "측정 전";
    return humidity.toFixed(1) + "%";
  };

  const formatDate = (dateString: string): string => {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
};

  return (
      <Senserstyle>
        <StyledDataItem>
          <StyledDataTitle>온도 :</StyledDataTitle>
          <StyledDataValue>
            {formatTemperature(latestInformation.sensorDataList.temperature)}
          </StyledDataValue>
        </StyledDataItem>
        <StyledDataItem>
          <StyledDataTitle>습도 :</StyledDataTitle>
          <StyledDataValue>
            {formatHumidity(latestInformation.sensorDataList.humidity)}
          </StyledDataValue>
        </StyledDataItem>
        <StyledDataItem>
          <StyledDataTitle>nh3 :</StyledDataTitle>
          <StyledDataValue>
            {latestInformation.sensorDataList.nh3
              ? latestInformation.sensorDataList.nh3
              : "측정 전"}
          </StyledDataValue>
        </StyledDataItem>
        <StyledDataItem>
          <StyledDataTitle>등록일자 :</StyledDataTitle>
          <StyledDataValue>
            {formatDate(latestInformation.regDttm)}
          </StyledDataValue>
        </StyledDataItem>
        <StyledDataItem>
          <StyledDataTitle>경과일 :</StyledDataTitle>
          <StyledDataValue>{latestInformation.elapsedTime}</StyledDataValue>
        </StyledDataItem>
        <StyledDataItem>
          <StyledDataTitle>유통기한 :</StyledDataTitle>
          <StyledDataValue>{latestInformation.expirationDate}</StyledDataValue>
        </StyledDataItem>
        <StyledDataItem>
          <StyledDataTitle>상태 :</StyledDataTitle>
          <StyledDataValue>{latestInformation.refresh}</StyledDataValue>
        </StyledDataItem>
      </Senserstyle>
  );
}
