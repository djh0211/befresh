import IdInputTextFields from "../atoms/IdInput"; // inputbox.tsx 파일의 컴포넌트를 import
import PasswordInputTextFields from "../atoms/PasswordInput";
import styled from "styled-components";
import MemberButton from "../atoms/MemberButton";
import LogoComponent from "../atoms/LogoComponent";

const LoginPageContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 100vh;
`;

const InputContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 70px;
  min-height: 30vh;
  min-width: 400px;
  width: 100%;
`;

function LoginPage() {
  return (
    <div>
      <LoginPageContainer>
        <LogoComponent />
        <InputContainer>
        <IdInputTextFields />
        <PasswordInputTextFields />
        <MemberButton>로그인</MemberButton>
      </InputContainer>
      </LoginPageContainer>
    </div>
  );
}

export default LoginPage;
