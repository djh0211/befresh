import styled from "styled-components";
import LoginForm from "../organisms/loginForm";

const LoginPageContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 90vh;
`;

interface LoginFormData {
  id: string;
  password: string
}


function LoginTemp( { onLogIn }: Readonly<{ onLogIn: (formData: LoginFormData) => void }>) {
  return (
    <div>
      <LoginPageContainer>
        <LoginForm onLogIn={ onLogIn } />
      </LoginPageContainer>
    </div>
  );
}

export default LoginTemp;
