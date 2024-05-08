import styled from "styled-components";
import LoginTemp from "../components/templates/loginTemp";
import { logIn } from "../api/member/memberApi";
import { useNavigate } from "react-router-dom";
import { saveTokens, getFcmToken } from "../utils/tokenUtils";
import { sendFcmToken } from "../api/alarm/alarmApi";

const LoginPageContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 90vh;
`;
interface LoginFormData {
  id: string;
  password: string;
}

function LoginPage() {
  const navigator = useNavigate();
  const fcmToken = getFcmToken();
  const handleLogIn = async (formData: LoginFormData) => {
    console.log("로그인폼데이타:", formData);
    try {
      const response = await logIn({...formData});
      console.log("실행은 됨:", response);
      if (response !== null) {
        // 헤더가 있는지 확인 후 토큰 추출
        console.log("헤더",response.headers)
        if (response.headers) {
          console.log("헤더있음")
          const accessToken = response.headers.get('Authorization');
          const refreshToken = response.headers.get('Authorization-refresh');
          if (accessToken && refreshToken) {
            // 토큰이 있는 경우에만 저장
            saveTokens(accessToken, refreshToken);

            // fcm 토큰도 같이 보내주기
            if (fcmToken) {
              sendFcmToken(fcmToken, accessToken)
            }
          }
        }
        navigator('/main');
      }
    } catch (error) {
      console.error("실행이 안됨:", error);
    }
  };

  return (
    <LoginPageContainer>
      <LoginTemp onLogIn={handleLogIn} />
    </LoginPageContainer>
  );
}

export default LoginPage;
