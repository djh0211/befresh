import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import MemberButton from '../atoms/MemberButton';

// StartPageContainer 스타일드 컴포넌트 정의
const StartPageContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 100vh;
`;

// LogoImage 스타일드 컴포넌트 정의
const LogoImage = styled.img`
  margin-bottom: 20px;
  min-height:50vh;
`;

// ButtonContainer 스타일드 컴포넌트 정의
const ButtonContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 70px;
  min-height: 30vh;
  min-width: 400px;
`;


// StartPage 컴포넌트 정의
const StartPage: React.FC = () => {
  return (
    <StartPageContainer>
      <LogoImage src="beFresh.png" alt="BeFresh 로고" />
      <ButtonContainer>
        <Link to="/login">
          <MemberButton>로그인</MemberButton>
        </Link>
        <Link to="/signup">
          <MemberButton>회원가입</MemberButton>
        </Link>
      </ButtonContainer>
    </StartPageContainer>
  );
};

export default StartPage;
