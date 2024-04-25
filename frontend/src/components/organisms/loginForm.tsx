import styled from "styled-components";
import LoginBlock from "../molecules/loginInputBlock";
import MemberButton from "../atoms/Button";
import LogoComponent from "../atoms/LogoComponent";

const LoginPageContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 90vh;
`;



function LoginForm() {
  return (
    <div>
      <LoginPageContainer>
        <LogoComponent />
        <LoginBlock />
        <MemberButton>로그인</MemberButton>
      </LoginPageContainer>
    </div>
  );
}

export default LoginForm;
