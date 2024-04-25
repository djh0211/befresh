import styled from "styled-components";
import LogoComponent from "../atoms/LogoComponent";
import SearchButton from "../atoms/Button";

const RegisterContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 90vh;
`;

function RegisterForm() {
  return (
    <div>
      <RegisterContainer>
        <LogoComponent />
        <SearchButton>내 냉장고 찾기</SearchButton>
      </RegisterContainer>
    </div>
  );
}

export default RegisterForm;