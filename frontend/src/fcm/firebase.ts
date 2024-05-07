import { initializeApp } from "firebase/app";
import { getMessaging, getToken } from 'firebase/messaging';
import { getAccessToken } from "../utils/tokenUtils";
import { saveFcmTokens } from "./fcmToken";
import { sendFcmToken } from "../api/alarm/alarmApi";

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
// 사용자 토큰 가져오기
const userToken = getAccessToken()
// valid key
const vapidKey = 'BJF1SSaQl6V23ngpxir8HC3FdZbu19PlMNbCgwram2XDFvF7az_V-cCUZ4LInD_ZLiLIK7wAF6q-TxZUmLlnRpw'
const messaging = getMessaging()

export function requestPermission() {
	Notification.requestPermission().then((permission) => {
		if (permission === 'granted') {
			console.log('Notification permission granted.');
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