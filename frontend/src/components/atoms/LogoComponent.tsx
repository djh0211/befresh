import styled from "styled-components";

const LogoImage = styled.img`
  width: 60vw; 
  height: 40vh;
  margin-bottom: 20px;
  
  /* 화면 너비가 800px 이상일 때 로고 크기 조절 */
  @media (min-width: 800px) {
    width: auto; 
    max-width: 400px; 
    max-height: 40vh; 
  }
`;

const LogoComponent = () => {
  return <LogoImage src="/beFresh.png" alt="BeFresh 로고" />;
};

export default LogoComponent;
