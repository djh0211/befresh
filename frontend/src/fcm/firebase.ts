import { initializeApp } from "firebase/app";
import { getMessaging, getToken } from 'firebase/messaging';

// fcm 정보
const config = {
    apiKey: "AIzaSyBuOsxURszu98gju5eopPd9Geid9NNDRx4",
    authDomain: "be-fresh-421400.firebaseapp.com",
    projectId: "be-fresh-421400",
    storageBucket: "be-fresh-421400.appspot.com",
    messagingSenderId: "860279932394",
    appId: "1:860279932394:web:6991c2879ff69b04719e93"
  };

// 시작하기
initializeApp(config)

// 토큰 가져오기
// valid key
const vapidKey = 'BJF1SSaQl6V23ngpxir8HC3FdZbu19PlMNbCgwram2XDFvF7az_V-cCUZ4LInD_ZLiLIK7wAF6q-TxZUmLlnRpw'
const messaging = getMessaging()

getToken(messaging, {vapidKey: vapidKey}).then((currnetToken)=> {
    if (currnetToken) {
        console.log(currnetToken)
    } else {
        console.log('알림 토큰을 가져오는 데에 실패!')
    }
}).catch((err) => console.log(err))