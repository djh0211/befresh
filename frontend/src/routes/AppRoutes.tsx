import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import StartPage from '../pages/StartPage';
import LoginPage from '../pages/LoginPage';
import SignupPage from '../pages/SignupPage';
import MainPage from '../pages/MainPage';
import InfoPage from '../pages/InfoPage';
import AlarmPage from '../pages/AlarmPage';

const AppRoutes = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<StartPage />} />
        <Route path="/login" element={<LoginPage />} />
        {/* <Route path="/signup" element={<SignupPage />} /> */}
        {/* <Route path="/signup/:refId" element={<SignupPage />} /> */}
        <Route path="/signup/*" element={<SignupPage />} />
        <Route path="/main" element={<MainPage />} />
        <Route path="/info" element={<InfoPage />} />
        <Route path="/alarm" element={<AlarmPage />} />
      </Routes>
    </Router>
  );
};

export default AppRoutes;
