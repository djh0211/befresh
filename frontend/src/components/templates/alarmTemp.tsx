import styled from 'styled-components';
import NavBlock from '../molecules/navBlock';
import AlarmForm from '../organisms/alarmForm';
import { alarmType } from '../../types/alarmTypes';
// 알람 페이지 템플릿

// 알람 타이틀
// 일괄 삭제 버튼
// 알림 리스트 들어갈 div
// navbar

const AlarmMain = styled.div`
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`
const AlarmTitle = styled.div`
  text-align: center;
  margin: 5vh 0 0 0;
  font-size: 4rem;
  font-weight: 900;
`

type propsType ={ 
  alarms: alarmType[],
  deleteAlarms: () => void
}

export default function AlarmTemplate({alarms, deleteAlarms}: propsType) {
  return (
    <AlarmMain>
      <AlarmTitle>알림</AlarmTitle>
      <AlarmForm alarms={alarms} deleteAlarms={deleteAlarms}/>
      <NavBlock/>
    </AlarmMain>
  );
}
