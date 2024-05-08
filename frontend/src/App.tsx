import AppRoutes from "./routes/AppRoutes";
import { useEffect } from "react";
// firebase 알림용
import { initializeApp } from "firebase/app";
import { getMessaging, getToken, onMessage } from "firebase/messaging";
import { getAccessToken, saveFcmTokens, getFcmToken } from "./utils/tokenUtils";
import { sendFcmToken } from "./api/alarm/alarmApi";
import { isAlarmType } from "./types/alarmTypes";
import { useDispatch } from "react-redux";
import { addAlarm, alertOn } from "./store/features/alarmSlice";


// fcm 정보
const config = {
  apiKey: "AIzaSyBuOsxURszu98gju5eopPd9Geid9NNDRx4",
  authDomain: "be-fresh-421400.firebaseapp.com",
  projectId: "be-fresh-421400",
  storageBucket: "be-fresh-421400.appspot.com",
  messagingSenderId: "860279932394",
  appId: "1:860279932394:web:6991c2879ff69b04719e93"
}
// valid key
const vapidKey = 'BJF1SSaQl6V23ngpxir8HC3FdZbu19PlMNbCgwram2XDFvF7az_V-cCUZ4LInD_ZLiLIK7wAF6q-TxZUmLlnRpw'


const App = () => {
  const dispatch = useDispatch()
  // 사용자 토큰 가져오기
  const userToken = getAccessToken()
  const fcmToken = getFcmToken()

  // 시작하기
  initializeApp(config)
  const messaging = getMessaging()

  useEffect(()=> {
    if (!fcmToken) {
      requestPermission(userToken, messaging)
    } 
  })

  // 메세지 받기
  onMessage(messaging, (payload) => {
    if (isAlarmType(payload)) {
      console.log('메세지 도착')
      dispatch(addAlarm(payload))
      dispatch(alertOn())
    } else {
      console.log('메세지 타입을 확인해주세요')
    }
  })
  return <AppRoutes />;
};

export default App;


function requestPermission(userToken: string|null, messaging: any) {
  Notification.requestPermission().then((permission) => {
    if (permission === 'granted') {
      console.log('알림권한 설정: true, token을 가져옵니다.');
      getToken(messaging, { vapidKey: vapidKey }).then((currentToken) => {
        if (currentToken) {
          saveFcmTokens(currentToken)
          if (userToken) {
            sendFcmToken(currentToken, userToken)
          } else {
            console.log('아직 로그인 하지 않음')
          }
        } else {
          console.log('알림 토큰을 가져오는 데에 실패!')
        }
      }).catch((err) => console.log(err))
    } else if (permission === 'denied') {
      console.log('푸시 권한이 차단되어 있음')
    }
  })
}
