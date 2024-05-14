import IdInputTextFields from "../atoms/IdInput";
import PasswordInputTextFields from "../atoms/PasswordInput";
import styled from "styled-components";
import React, { useState } from 'react';

const InputContainer = styled.div`
  display: flex;
  flex-direction: column;
  min-height: 30vh;
  min-width: 400px;
  width: 100%;
`;

interface LogInInputBlockProps {
  onIdChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
  onPasswordChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
  onKeyDown: (event: React.KeyboardEvent<HTMLInputElement>) => void;
}

function LoginInputBlock( {onIdChange, onPasswordChange, onKeyDown}: Readonly<LogInInputBlockProps>) { // 수정
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

  const onCheckEnter = (event: React.KeyboardEvent<HTMLInputElement>) => { // 수정
    if(event.key === 'Enter') {
      onKeyDown(event); // 추가
    }
  };

  return (
    <div>
      <InputContainer>
        <IdInputTextFields onChange={handleIdChange} onKeyDown={onCheckEnter} />
        <PasswordInputTextFields label="Password" onChange={handlePasswordChange} onKeyDown={onCheckEnter} />
      </InputContainer>
    </div>
  );
}

export default LoginInputBlock;
