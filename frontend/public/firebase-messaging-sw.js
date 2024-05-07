// 공식문서 그대로 복붙
importScripts('https://www.gstatic.com/firebasejs/8.10.0/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/8.10.0/firebase-messaging.js');


const config = {
	apiKey: "AIzaSyBuOsxURszu98gju5eopPd9Geid9NNDRx4",
	authDomain: "be-fresh-421400.firebaseapp.com",
	projectId: "be-fresh-421400",
	storageBucket: "be-fresh-421400.appspot.com",
	messagingSenderId: "860279932394",
	appId: "1:860279932394:web:6991c2879ff69b04719e93"
};

const firebaseApp = firebase.initializeApp(config)

const messaging = firebase.messaging();