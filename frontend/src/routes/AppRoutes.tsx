import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import StartPage from '../components/pages/StartPage';
import LoginPage from '../components/pages/LoginPage';
import SignupPage from '../components/pages/SignupPage';
import MainPage from '../components/pages/MainPage';
import RegisterPage from '../components/pages/RegisterPage';
import UnRegisterPage from '../components/pages/UnRegisterPage';
import AlarmPage from '../components/pages/AlarmPage';

const AppRoutes = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<StartPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/signup" element={<SignupPage />} />
        <Route path="/main" element={<MainPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/unregister" element={<UnRegisterPage />} />
        <Route path="/alarm" element={<AlarmPage />} />
      </Routes>
    </Router>
  );
};

export default AppRoutes;
