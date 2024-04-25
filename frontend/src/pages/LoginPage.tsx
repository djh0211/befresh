import styled from "styled-components";
import LoginTemp from "../components/templates/loginTemp";

const LoginPageContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 90vh;
`;



function LoginPage() {
  return (
    <div>
      <LoginPageContainer>
        <LoginTemp/>
      </LoginPageContainer>
    </div>
  );
}

export default LoginPage;
