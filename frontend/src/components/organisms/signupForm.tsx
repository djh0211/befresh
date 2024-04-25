import styled from "styled-components";
import SignupInputBlock from "../molecules/sighupInputBlock";
import MemberButton from "../atoms/Button";
import LogoComponent from "../atoms/LogoComponent";

const SignUpContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 90vh;
`;



function SignUpForm() {
  return (
    <div>
      <SignUpContainer>
        <LogoComponent />
        <SignupInputBlock />
        <MemberButton>회원가입</MemberButton>
      </SignUpContainer>
    </div>
  );
}

export default SignUpForm;
