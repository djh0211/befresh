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
  refrigeratorId: string;
}

function SignUpTemp({ onSignUp, refId, getRefId }: Readonly<{ onSignUp: (formData: SignUpFormData) => void, refId: string|null, getRefId: (ref:string) => void }>) {
  return (
    <div>
      <SignUpTempContainer>
        <SignUpForm onSignUp={onSignUp} refId={refId} getRefId={getRefId}/>
      </SignUpTempContainer>
    </div>
  );
}

export default SignUpTemp;
