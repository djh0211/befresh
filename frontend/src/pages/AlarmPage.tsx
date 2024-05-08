import AlarmTemplate from "../components/templates/alarmTemp";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "../store/store";
import { deleteAllAlarms, deleteOneAlarm, alertOff } from "../store/features/alarmSlice";
import { useEffect } from "react";

// 알림 fcm으로 받아와서 넣기
function AlarmPage() {
  const dispatch = useDispatch()
  const alarms = useSelector((state:RootState) => state.alarms)
  // 전체 알람 삭제
  const deleteAlarms = () => {
    dispatch(deleteAllAlarms())
  }
  // 특정 알람 하나만 삭제
  const deleteOne = (id :string) => {
    dispatch(deleteOneAlarm(id))
  }

  // 알람 페이지에 오면 알람 우선 꺼줌
  useEffect(() => {
    dispatch(alertOff())
  }, [])

  return (
    <div>
      <AlarmTemplate alarms={alarms.alarms} deleteAlarms={deleteAlarms} deleteOne={deleteOne}/>
    </div>
  );
}

export default AlarmPage;
