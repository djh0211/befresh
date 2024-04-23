import { BrowserRouter as Router, Routes, } from 'react-router-dom';
import AppRoutes from './routes/AppRoutes'; // 라우트 관련 파일을 import
import Header from './components/Header'; // 전역 헤더 컴포넌트를 import

const App = () => {
  return (
        <AppRoutes />
  );
};

export default App;
