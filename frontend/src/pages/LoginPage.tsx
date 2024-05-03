import styled from "styled-components";
import LoginTemp from "../components/templates/loginTemp";
import { logIn } from "../api/member/memberApi";
import { useNavigate } from "react-router-dom";

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
  const navigator = useNavigate()
  const handleLogIn = async (formData: LoginFormData) => {
    try {
      const response = await logIn({...formData})
      console.log("실행은 됨:",response);
      if (response !== 0) {
        navigator('/main')
      }
    } catch (error) {
      console.error("실행이 안됨:",error)
    }
  }

  return (
      <LoginPageContainer>
        <LoginTemp onLogIn={handleLogIn} />
      </LoginPageContainer>
  );
}

export default LoginPage;
