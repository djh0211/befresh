import IdInputTextFields from "../atoms/IdInput"; // inputbox.tsx 파일의 컴포넌트를 import
import PasswordInputTextFields from "../atoms/PasswordInput";
import styled from "styled-components";


const InputContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 70px;
  min-height: 30vh;
  min-width: 400px;
  width: 100%;
`;

function SignupInputBlock() {
  return (
    <div>
      <InputContainer>
        <IdInputTextFields />
        <PasswordInputTextFields label="비밀번호" />
        <PasswordInputTextFields label="비밀번호 확인"/>
      </InputContainer>
    </div>
  );
}

export default SignupInputBlock;
