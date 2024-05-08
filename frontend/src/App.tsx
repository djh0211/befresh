import AppRoutes from "./routes/AppRoutes"; 
import { requestPermission } from './fcm/firebase'
import { getFcmToken } from "./fcm/fcmToken";

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
        navigator.serviceWorker.getRegistration().then(registration => {
          if (registration) {
            registration.unregister().then(() => {
              console.log('Service Worker unregistered successfully.');
            }).catch(error => {
              console.error('Service Worker unregistration failed:', error);
            });
          }
        });
      }
    });
  }


  const fcmToken = getFcmToken()
  if (!fcmToken) {
    requestPermission(); 
  }
  return <AppRoutes />;
};

export default App;