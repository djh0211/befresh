import React, { useState } from 'react';
import IdInputTextFields from '../atoms/IdInput';
import PasswordInputTextFields from '../atoms/PasswordInput';
import styled from 'styled-components';

interface SignupInputBlockProps {
  onIdChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
  onPasswordChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
  onConfirmPasswordChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
}

const InputContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 70px;
  min-height: 30vh;
  min-width: 400px;
  width: 100%;
`;

function SignupInputBlock({ onIdChange, onPasswordChange, onConfirmPasswordChange }: Readonly<SignupInputBlockProps>) {
  // 아이디를 위한 state 선언
  const [_id, setId] = useState('');

  // 아이디 입력 값 변경 시 호출되는 이벤트 핸들러
  const handleIdChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setId(event.target.value);
    onIdChange(event); // 부모 컴포넌트로 이벤트 전달
  };

  // 비밀번호와 비밀번호 확인을 위한 state 선언
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  // 비밀번호 입력 값 변경 시 호출되는 이벤트 핸들러
  const handlePasswordChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value);
    onPasswordChange(event); // 부모 컴포넌트로 이벤트 전달
  };

  // 비밀번호 확인 입력 값 변경 시 호출되는 이벤트 핸들러
  const handleConfirmPasswordChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setConfirmPassword(event.target.value);
    onConfirmPasswordChange(event); // 부모 컴포넌트로 이벤트 전달
  };

  // 비밀번호와 비밀번호 확인이 다를 때 helperText를 표시할지 여부를 결정하는 함수
  const getPasswordHelperText = (): string => {
    if (password !== confirmPassword) {
      return '같은 비밀번호를 입력해주세요';
    }
    return ''; // 다를 경우에는 빈 문자열 반환하여 helperText를 표시하지 않음
  };

  return (
    <InputContainer>
      <IdInputTextFields onChange={handleIdChange} /> {/* 아이디 입력 필드 */}
      <PasswordInputTextFields
        label="비밀번호"
        onChange={handlePasswordChange} // 비밀번호 입력 값 변경 시 호출되는 핸들러
      />
      <PasswordInputTextFields
        label="비밀번호 확인"
        onChange={handleConfirmPasswordChange} // 비밀번호 확인 입력 값 변경 시 호출되는 핸들러
        helperText={getPasswordHelperText()} // 비밀번호 확인 입력란의 helperText 설정
      />
    </InputContainer>
  );
}

export default SignupInputBlock;
