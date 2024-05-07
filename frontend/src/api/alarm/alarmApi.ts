import axios from "axios"

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

export {sendFcmToken}