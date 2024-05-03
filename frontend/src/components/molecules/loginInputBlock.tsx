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

interface LogInInputBlockProps {
  onIdChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
  onPasswordChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
}

function LoginInputBlock( {onIdChange, onPasswordChange}: Readonly<LogInInputBlockProps>) {
  const [_id, setId] = useState(''); 
  const [_password, setPassword] = useState(''); 

  const handleIdChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setId(event.target.value);
    onIdChange(event);
  };

  const handlePasswordChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value);
    onPasswordChange(event);
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

export default LoginInputBlock;
