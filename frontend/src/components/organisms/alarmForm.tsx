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
  height: 65vh;
`

type propsType ={ 
  alarms: alarmType[],
  deleteAlarms: () => void
}

export default function AlarmForm({alarms, deleteAlarms}: propsType) {
  return (
    <AlarmBox>
      <ButtonContainer>
        <Button 
          variant='outlined' 
          color='error'
          onClick={deleteAlarms}
          sx={{height:'50px', marginRight:'5%', fontSize:'1.4rem'}}
        >
          일괄 삭제
        </Button>
      </ButtonContainer>
      <AlarmList>
        {
          alarms.map((alarm, idx) => {
            return(
              <AlarmBlock key={idx} type={alarm.type} content={alarm.content}/>
            )
          })
        }
      </AlarmList>
    </AlarmBox>
  );
}
