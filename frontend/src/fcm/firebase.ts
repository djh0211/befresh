import { initializeApp } from "firebase/app";
import { getMessaging, getToken } from 'firebase/messaging';
import axios from "axios";
import { getAccessToken } from "../utils/tokenUtils";

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
			getToken(messaging, { vapidKey: vapidKey }).then((currnetToken) => {
				if (currnetToken) {
					console.log(currnetToken)
					axios.post('https://be-fresh.site/api/member/fcmToken', {
						'fcmToken': currnetToken
					},{
						headers: {
							'Authorization' : `Bearer ${userToken}`
						}
					}).then((res) => console.log(res))
					.catch((err) => console.log(err))
				} else {
					console.log('알림 토큰을 가져오는 데에 실패!')
				}
			}).catch((err) => console.log(err))
		} else if (permission === 'denied') {
			console.log('푸시 권한이 차단되어 있음')
		}
	})
}

// getToken(messaging, { vapidKey: vapidKey }).then((currnetToken) => {
// 	if (currnetToken) {
// 		console.log(currnetToken)
// 	} else {
// 		console.log('알림 토큰을 가져오는 데에 실패!')
// 	}
// }).catch((err) => console.log(err))