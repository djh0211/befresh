import styled from "styled-components";

const LogoImage = styled.img`
  width: 60vw; 
  height: 40vh;
  margin-bottom: 20px;
`;

const LogoComponent = () => {
  return <LogoImage src="/beFresh.png" alt="BeFresh 로고" />;
};

export default LogoComponent;
