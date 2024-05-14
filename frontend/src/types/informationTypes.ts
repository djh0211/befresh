// 센서 데이터 한개의 타입
type sensorDataType = {
  type :string,
  value :number,
  time :string
}

// api로 오는 데이터 전체
type informationType = {
  name :string,
  qrId :string,
  regDttm : string,
  elapsedTime: number,
  expirationDate: string | null,
  refresh: string,
  sensorDataList: {
    temperature: sensorDataType[],
    humidity: sensorDataType[],
    nh3: sensorDataType[],
  }
}

// 최신 데이터 타입
type latestInformationType = {
  // 등록일자
  regDttm : string,
  // 경과시간
  elapsedTime: number,
  // 유통기한
  expirationDate: string | null,
  // 현재상태
  refresh: string,
  sensorDataList: {
    temperature: number | null,
    humidity: number | null,
    nh3: number | null,
  }
}

function isSensorDataType(payload:any):payload is sensorDataType {
  return (
    typeof payload === 'object' &&
    payload !== null &&
    'type' in payload &&
    typeof payload.type === 'string' &&
    'value' in payload &&
    typeof payload.value === 'number' &&
    'time' in payload &&
    typeof payload.time === 'string'
  )
}

function isInformationType(payload: any): payload is informationType {
  return (
    typeof payload === 'object' &&
    payload !== null &&
    'name' in payload &&
    typeof payload.name === 'string' &&
    'qrId' in payload &&
    typeof payload.qrId === 'string' &&
    'regDttm' in payload &&
    typeof payload.regDttm === 'string' &&
    'elapsedTime' in payload &&
    typeof payload.elapsedTime === 'number' &&
    'expirationDate' in payload &&
    (typeof payload.expirationDate === 'string' || payload.expirationDate === null) &&
    'refresh' in payload &&
    typeof payload.refresh === 'string' &&
    'sensorDataList' in payload &&
    typeof payload.sensorDataList === 'object' &&
    'temperature' in payload.sensorDataList &&
    (Array.isArray(payload.sensorDataList.temperature) || payload.sensorDataList.temperature.length === 0) &&
    (payload.sensorDataList.temperature.length === 0 || payload.sensorDataList.temperature.every(isSensorDataType)) &&
    'humidity' in payload.sensorDataList &&
    (Array.isArray(payload.sensorDataList.humidity) || payload.sensorDataList.humidity.length === 0) &&
    (payload.sensorDataList.humidity.length === 0 || payload.sensorDataList.humidity.every(isSensorDataType)) &&
    'nh3' in payload.sensorDataList &&
    (Array.isArray(payload.sensorDataList.nh3) || payload.sensorDataList.nh3.length === 0) &&
    (payload.sensorDataList.nh3.length === 0 || payload.sensorDataList.nh3.every(isSensorDataType))
  );
}

export {isSensorDataType, isInformationType}
export type {sensorDataType, informationType, latestInformationType}