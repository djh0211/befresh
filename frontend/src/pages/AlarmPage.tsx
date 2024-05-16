import AlarmTemplate from "../components/templates/alarmTemp";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "../store/store";
import { alertOff } from "../store/features/alarmSlice";
import { useEffect, useState } from "react";
import { getAlarms, deleteAllAlarmsApi, deleteOneAlarmsApi } from "../api/alarm/alarmApi";
import { getAccessToken } from "../utils/tokenUtils";
import { alarmType } from "../types/alarmTypes";

// 알림 fcm으로 받아와서 넣기
function AlarmPage() {
  const token = getAccessToken()
  const dispatch = useDispatch()
  const alarms = useSelector((state:RootState) => state.alarms)
  const [alarmList, setAlarmList] = useState<alarmType[]>([])
  const [message, setMessage] = useState<string>('알림 데이터 로딩 중')

  // 전체 알람 삭제 api요청도 같이 보내주기
  const deleteAlarms = () => {
    if (token) {
      deleteAllAlarmsApi(token)
    }
    setAlarmList([])
    setMessage('알림이 없습니다.')
  }
  // 특정 알람 하나만 삭제
  const deleteOne = (id :string) => {
    if (token) {
      const temp = alarmList.filter((alarm) => {
        return alarm.data.notificationId != id
      })
      setAlarmList(temp)
      deleteOneAlarmsApi(token, id)
    }
  }

  // 알람 저장
  const saveAlarm = async (token:string) => {
    const apiAlarms = await getAlarms(token)
    const filteredAlarm = apiAlarms.filter((alarm) => {
      return alarms.categories.includes(alarm.data.category)
    })
    setAlarmList(filteredAlarm)
    setMessage('현재 알림이 없습니다.')
  }

  // 알람 페이지에 오면 알람 우선 꺼줌
  // 알림 올 때마다 업데이트
  useEffect(() => {
    dispatch(alertOff())
    if (token) {
      saveAlarm(token)
    }
  }, [alarms.alert])

  // 메세지 변경
  const changeMessage = (newMessage:string) => {
    setMessage(newMessage)
  }

  return (
    <div>
      <AlarmTemplate message={message} changeMessage={changeMessage} alarms={alarmList} deleteAlarms={deleteAlarms} deleteOne={deleteOne}/>
    </div>
  );
}

export default AlarmPage;
