import AppRoutes from "./routes/AppRoutes"; 
import {requestPermission} from './fcm/firebase'

// requestPermission();

const App = () => {
  return <AppRoutes />;
};

export default App;