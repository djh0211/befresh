import styled from 'styled-components';
import NavBlock from '../molecules/navBlock';
import AlarmForm from '../organisms/alarmForm';
import { alarmType } from '../../types/alarmTypes';
import AlarmModalForm from '../organisms/alarmModalForm';
// 알람 페이지 템플릿

// 알람 타이틀
// 일괄 삭제 버튼
// 알림 리스트 들어갈 div
// navbar

const AlarmMain = styled.div`
  position: relative;
  height: 92vh;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  margin-bottom: 80px;
`
const AlarmTitle = styled.div`
  text-align: center;
  margin: 5vh 0 2vh 0;
  font-size: 4rem;
  font-weight: 900;
`
const NavBlockWrapper = styled.div`
  position: fixed;
  bottom: 0;
  width: 100%;
  background-color: #ffffff; 
  z-index: 999; 
`;

type propsType ={ 
  message :string,
  changeMessage: (newMessage: string) => void,
  alarms: alarmType[],
  deleteAlarms: () => void,
  deleteOne: (id :string) => void
}

export default function AlarmTemplate({message, changeMessage, alarms, deleteAlarms, deleteOne}: propsType) {
  return (
    <AlarmMain>
      <AlarmModalForm/>
      <AlarmTitle>알림</AlarmTitle>
      <AlarmForm message={message} changeMessage={changeMessage} alarms={alarms} deleteAlarms={deleteAlarms} deleteOne={deleteOne}/>
      <NavBlockWrapper>
        <NavBlock />
      </NavBlockWrapper>
    </AlarmMain>
  );
}
