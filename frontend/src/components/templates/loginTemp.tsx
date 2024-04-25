import styled from "styled-components";
import LoginForm from "../organisms/loginForm";

const LoginPageContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 90vh;
`;


function LoginTemp() {
  return (
    <div>
      <LoginPageContainer>
        <LoginForm />
      </LoginPageContainer>
    </div>
  );
}

export default LoginTemp;
