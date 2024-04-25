import styled from "styled-components";
import RegisterForm from "../organisms/registerForm";

const RegisterContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 90vh;
`;


function RegisterTemp() {
  return (
    <div>
      <RegisterContainer>
        <RegisterForm />
      </RegisterContainer>
    </div>
  );
}

export default RegisterTemp;
