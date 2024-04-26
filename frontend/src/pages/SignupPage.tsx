import styled from "styled-components";
import SignUpTemp from "../components/templates/signupTemp";

const SignUpPageContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 90vh;
`;


function SignupPage() {
  return (
    <div>
    <SignUpPageContainer>
      <SignUpTemp/>
    </SignUpPageContainer>
  </div>
  );
}

export default SignupPage;
