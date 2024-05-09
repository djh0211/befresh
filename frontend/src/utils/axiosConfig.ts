import axios from 'axios';
import { getAccessToken, getRefreshToken, removeTokens, saveTokens } from './tokenUtils';


const axiosInstance = axios.create();

// Request interceptor 설정
axiosInstance.interceptors.request.use(
  async (config) => {
    console.log('헤더에 토큰 넣기전');

    const accessToken = getAccessToken();
    if (accessToken) {
      config.headers['Authorization'] = `Bearer ${accessToken}`;
      console.log('헤더에 토큰 넣음');
    } 
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Response interceptor 설정
axiosInstance.interceptors.response.use(
  (response) => {
    return response;
  },
  async (error) => {
    const originalRequest = error.config;
    if (error.response && error.response.status === 403 && !originalRequest._retry) {
      originalRequest._retry = true;
      const refreshToken = getRefreshToken();
      if (refreshToken) {
        try {
          console.log('새 토큰 내놔');
          // 리프레시 토큰으로 새로운 액세스 토큰 요청
          const refreshRequest = {
            url: originalRequest.url,
            method: originalRequest.method,
            headers: {
              'Authorization-refresh': `Bearer ${refreshToken}`
            }
          };
        
          const response = await axios(refreshRequest);
          console.log('res', response)
          // const response = await axios.post('https://be-fresh.site/api/refresh-token', { refreshToken });
          if (response.headers) {            
            const newAccessToken = response.headers['authorization']
            saveTokens(newAccessToken, refreshToken);
            originalRequest.headers['authorization'] = `Bearer ${newAccessToken}`;
            return axiosInstance(originalRequest);
          }
          // 새로운 액세스 토큰 로컬 스토리지에 저장
          
          // 새로운 액세스 토큰으로 원래 요청 재시도
        } catch (refreshError) {
          logout();
        }
      }
    }
    return Promise.reject(error);
  }
);

// 로그아웃 함수
const logout = () => {
  // 로컬 스토리지에서 토큰 제거
  removeTokens();
  // 로그인 페이지로 리다이렉트 또는 다른 처리
  // 리다이렉트 처리
  window.location.href = '/login';
};

export default axiosInstance;
