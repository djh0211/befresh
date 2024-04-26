import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import MemberButton from '../components/atoms/Button';
import LogoComponent from '../components/atoms/LogoComponent';

// StartPageContainer 스타일드 컴포넌트 정의
const StartPageContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 90vh;
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
      <LogoComponent />
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
