import axios from "axios"
import { alarmType } from "../../types/alarmTypes"

// 회원정보와 fcm토큰 묶어서 보내주는 api
function sendFcmToken(fcmToken : string, userToken:string) {
  axios.post('https://be-fresh.site/api/member/fcmToken', {
    'fcmToken': fcmToken
  },{
    headers: {
      'Authorization' : `Bearer ${userToken}`
    }
  }).then(() => console.log('알람토큰 등록완료!'))
  .catch((err) => console.log(err))
}


// 알람 전체 조회
async function getAlarms(token: string) :Promise<alarmType[]> {
  let tempAlarms:alarmType[] = []
  await axios.get('https://be-fresh.site/api/notification',{
    headers: {
      'Authorization' : `Bearer ${token}`
    }
  }).then((res) => {
    console.log(res)
    tempAlarms = res.data.result
    return tempAlarms
  })
  .catch((err) => {
    console.log(err)
  })
  return tempAlarms
}


// 전체 알람 삭제
function deleteAllAlarmsApi(token:string) {
  axios.delete('https://be-fresh.site/api/notification/all', {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  })
}

// 특정 알람 삭제
function deleteOneAlarmsApi(token:string, id:string) {
  axios.delete(`https://be-fresh.site/api/notification?notificationId=${id}`, {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  })
}


export {sendFcmToken, getAlarms, deleteAllAlarmsApi, deleteOneAlarmsApi}