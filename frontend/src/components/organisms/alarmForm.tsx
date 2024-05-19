import styled from "styled-components";
import AlarmBlock from "../molecules/alarmBlock";
import { Button } from "@mui/material";
import { alarmType } from "../../types/alarmTypes";

const AlarmBox = styled.div`
  width: 100vw;
`
const ButtonContainer = styled.div`
  display: flex;
  justify-content: flex-end;
`
const AlarmList = styled.div`
  width: 90%;
  background-color: rgba(161, 193, 167, 0.2);
  margin: 3% auto;
  padding: 2vh 0;
  border-radius: 10px;
  height: 70vh;
  overflow: scroll;
`

const MessageDiv = styled.div`
  display: flex;
  flex-direction: column;
`

const MessageP = styled.p`
  text-align: center; 
  font-size: 2rem;
  color: grey;
`

type propsType ={ 
  message: string,
  changeMessage: (newMessage:string) => void,
  alarms: alarmType[],
  deleteAlarms: () => void,
  deleteOne: (id :string) => void
}

export default function AlarmForm({message, changeMessage, alarms, deleteAlarms, deleteOne}: propsType) {
  const checkPermission = () => {
    // 알림 권한 요청
    Notification.requestPermission().then((res) => {
      // 권한 설정이 되면, 새로고침해서 알림 불러옴
      if (res == 'granted') {
        window.location.reload()
      } else {
        // 권한을 거절했을 때
        changeMessage('알림 권한이 없습니다. 설정을 확인해주세요')
      }
    }).catch(() => {
      // 이미 거절된 상태일 때
      changeMessage('알림 권한이 없습니다. 설정을 확인해주세요')
    })
  }
  return (
    <AlarmBox>
      <ButtonContainer>
        <Button 
          variant='outlined' 
          color='error'
          onClick={deleteAlarms}
          sx={{height:'70px', marginRight:'5%', fontSize:'1.6rem'}}
        >
          일괄 삭제
        </Button>
      </ButtonContainer>
      <AlarmList>
        {
          alarms.length == 0 ? (
            <MessageDiv>
              <MessageP>
                {message}
              </MessageP>
              <Button 
                onClick={checkPermission}
                size="large"
                variant="contained"
                color="success"
                sx={{width: '35%', marginX:'auto', fontSize:'1.6rem'}}
              >
                알림 권한 확인하기
              </Button>
            </MessageDiv>
            
          ) :
          alarms.map((alarm, idx) => {
            return(
              <AlarmBlock 
                key={idx} 
                id={alarm.data.notificationId} 
                type={alarm.data.category} 
                title={alarm.notification.title}
                content={alarm.notification.body} 
                deleteOne={deleteOne}
              />
            )
          })
        }
      </AlarmList>
    </AlarmBox>
  );
}
