import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import StartPage from '../pages/StartPage';
import LoginPage from '../pages/LoginPage';
import SignupPage from '../pages/SignupPage';
import MainPage from '../pages/MainPage';
import RegisterPage from '../pages/RegisterPage';
import UnRegisterPage from '../pages/UnRegisterPage';
import AlarmPage from '../pages/AlarmPage';

const AppRoutes = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<StartPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/signup" element={<SignupPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/main" element={<MainPage />} />
        <Route path="/unregister" element={<UnRegisterPage />} />
        <Route path="/alarm" element={<AlarmPage />} />
      </Routes>
    </Router>
  );
};

export default AppRoutes;
