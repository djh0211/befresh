import AppRoutes from "./routes/AppRoutes"; 
import { requestPermission } from './fcm/firebase'
import { getFcmToken } from "./fcm/fcmToken";

const App = () => {
  if('serviceWorker' in navigator) {
    navigator.serviceWorker.register('./sw.js', { updateViaCache: 'none' }).then(function(registration) {
      console.log('ServiceWorker registration successful with scope: ', registration.active);
    });
  };

  const fcmToken = getFcmToken()
  if (!fcmToken) {
    requestPermission(); 
  }
  return <AppRoutes />;
};

export default App;