import AppRoutes from "./routes/AppRoutes"; 
import { requestPermission } from './fcm/firebase'
import { getFcmToken } from "./fcm/fcmToken";

const App = () => {
  const fcmToken = getFcmToken()
  if (!fcmToken) {
    requestPermission(); 
  }
  return <AppRoutes />;
};

export default App;