import styled from "styled-components";
import SignUpForm from "../organisms/signupForm";

const SignUpTempContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 90vh;
`;


function SignUpTemp() {
  return (
    <div>
      <SignUpTempContainer>
        <SignUpForm />
      </SignUpTempContainer>
    </div>
  );
}

export default SignUpTemp;
