import AlarmTemplate from "../components/templates/alarmTemp";
import { useState } from "react";
import { alarmType } from "../types/alarmTypes";


// 알림 fcm으로 받아와서 넣기
function AlarmPage() {
  const [alarms, setAlarms] = useState<alarmType[]>([
    {
      type: 'warning',
      content: '닭고기를 냉장고에 넣은지 4일이 지났습니다. 확인해주세요.'
    },    
    {
      type: 'add',
      content: '오리고기가 등록되었습니다.'
    },
    {
      type: 'add',
      content: '돼지고기가 등록되었습니다.'
    } 
  ])
  const deleteAlarms = () => {
    setAlarms([])
  }
  return (
    <div>
      <AlarmTemplate alarms={alarms} deleteAlarms={deleteAlarms}/>
    </div>
  );
}

export default AlarmPage;
