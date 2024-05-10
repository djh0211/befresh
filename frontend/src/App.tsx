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
  // if('serviceWorker' in navigator) {
  //   navigator.serviceWorker.register('/sw.js', { updateViaCache: 'none' }).then(function(registration) {
  //     console.log('ServiceWorker registration successful with scope: ', registration.active);
  //   });
  // };

    // 서비스 워커 등록
    if ('serviceWorker' in navigator) {
      window.addEventListener('load', () => {
        // 서비스 워커 예외 경로 설정
        if (!window.location.pathname.includes('/jenkins/') || !window.location.pathname.includes('/sonar/')) {
          navigator.serviceWorker.register('/sw.js', { scope: '/' })
            .then(registration => {
              console.log('Service Worker registered with scope:', registration.scope);
            })
            .catch(error => {
              console.error('Service Worker registration failed:', error);
            });
        } else {
          // /jenkins 경로로 들어왔을 때 등록된 서비스 워커 해제
          navigator.serviceWorker.getRegistrations().then(function (registrations) {
            if (!registrations.length) {
              console.log('No serviceWorker registrations found.')
              return;
            }
            for (let registration of registrations) {
              registration.unregister().then(function (boolean) {
                console.log(
                  (boolean ? 'Successfully unregistered' : 'Failed to unregister'), 'ServiceWorkerRegistration\n' +
                  (registration.installing ? '  .installing.scriptURL = ' + registration.installing.scriptURL + '\n' : '') +
                  (registration.waiting ? '  .waiting.scriptURL = ' + registration.waiting.scriptURL + '\n' : '') +
                  (registration.active ? '  .active.scriptURL = ' + registration.active.scriptURL + '\n' : '') +
                  '  .scope: ' + registration.scope + '\n'
                );
              });
            }
          });
        }
      });
    }

  if ('caches' in window) {
    caches.keys()
      .then(function(keyList) {
          return Promise.all(keyList.map(function(key) {
              return caches.delete(key);
          }));
      })
  }

  const fcmToken = getFcmToken()
  const userToken = getAccessToken()
  const dispatch = useDispatch()

  // 시작하기
  initializeApp(config)
  const messaging = getMessaging()

  // fcmToken이 없다면 가져오기
  useEffect(()=> {
    if (!fcmToken) {
      requestPermission(userToken, messaging)
    }
  }, [])

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
