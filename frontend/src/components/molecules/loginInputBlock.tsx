import IdInputTextFields from "../atoms/IdInput"; // inputbox.tsx 파일의 컴포넌트를 import
import PasswordInputTextFields from "../atoms/PasswordInput";
import styled from "styled-components";
import React, { useState } from 'react';

const InputContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 70px;
  min-height: 30vh;
  min-width: 400px;
  width: 100%;
`;

function LoginBlock() {
  const [id, setId] = useState(''); // eslint-disable-line no-unused-vars
  const [password, setPassword] = useState(''); // eslint-disable-line no-unused-vars

  const handleIdChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setId(event.target.value);
  };

  const handlePasswordChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value);
  };

  return (
    <div>
      <InputContainer>
        <IdInputTextFields onChange={handleIdChange} />
        <PasswordInputTextFields label="Password" onChange={handlePasswordChange} />
      </InputContainer>
    </div>
  );
}

export default LoginBlock;
