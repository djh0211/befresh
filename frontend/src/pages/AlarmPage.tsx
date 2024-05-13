import AlarmTemplate from "../components/templates/alarmTemp";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "../store/store";
import { addAlarms, deleteAllAlarms, deleteOneAlarm, alertOff } from "../store/features/alarmSlice";
import { useEffect } from "react";
import { getAlarms, deleteAllAlarmsApi, deleteOneAlarmsApi } from "../api/alarm/alarmApi";
import { getAccessToken } from "../utils/tokenUtils";

// 알림 fcm으로 받아와서 넣기
function AlarmPage() {
  const token = getAccessToken()
  const dispatch = useDispatch()
  const alarms = useSelector((state:RootState) => state.alarms)
  // 전체 알람 삭제 api요청도 같이 보내주기
  const deleteAlarms = () => {
    if (token) {
      dispatch(deleteAllAlarms())
      deleteAllAlarmsApi(token)
    }
  }
  // 특정 알람 하나만 삭제
  const deleteOne = (id :string) => {
    if (token) {
      dispatch(deleteOneAlarm(id))
      deleteOneAlarmsApi(token, id)
    }
  }

  // 알람 저장
  const saveAlarm =async (token:string) => {
    const apiAlarms = await getAlarms(token)
    dispatch(addAlarms(apiAlarms))
  }

  // 알람 페이지에 오면 알람 우선 꺼줌
  useEffect(() => {
    // 새로고침하면 있던거 삭제하고 api로 가져옴
    dispatch(deleteAllAlarms())
    if (token) {
      saveAlarm(token)
    }
    dispatch(alertOff())
  }, [])

  return (
    <div>
      <AlarmTemplate alarms={alarms.alarms} deleteAlarms={deleteAlarms} deleteOne={deleteOne}/>
    </div>
  );
}

export default AlarmPage;
