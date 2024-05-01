import styled from "styled-components";
import SignUpForm from "../organisms/signupForm";

const SignUpTempContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 90vh;
`;

interface SignUpFormData {
  id: string;
  password: string;
  refrigeratorId: number;
}

function SignUpTemp({ onSignUp }: Readonly<{ onSignUp: (formData: SignUpFormData) => void }>) {
  return (
    <div>
      <SignUpTempContainer>
        <SignUpForm onSignUp={onSignUp} />
      </SignUpTempContainer>
    </div>
  );
}

export default SignUpTemp;
