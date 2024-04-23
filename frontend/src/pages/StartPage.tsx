import styled from 'styled-components';
import { Link } from 'react-router-dom';
import Button from '../atoms/MemberButton';

const StartPageContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const LogoImage = styled.img`
  margin-bottom: 20px; /* 로고 아래 간격 조절 */
`;

const ButtonContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 10px; /* 버튼 사이 간격 조절 */
`;

const ResponsiveButton = styled(Button)`
  width: 200px; /* 버튼의 너비를 고정 */
`;

const StartPage: React.FC = () => {
  return (
    <StartPageContainer>
      <LogoImage src="beFresh.png" alt="BeFresh 로고" />

      <ButtonContainer>
        <Link to="/login">
          <ResponsiveButton>로그인</ResponsiveButton>
        </Link>
        <Link to="/signup">
          <ResponsiveButton>회원가입</ResponsiveButton>
        </Link>
      </ButtonContainer>
    </StartPageContainer>
  );
};

export default StartPage;
