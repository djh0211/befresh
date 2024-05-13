import NavBlock from "../molecules/navBlock";
import styled from "styled-components";
import InfoForm from "../organisms/InfoForm";

const InfoMain = styled.div`
  width: 100vw;
  height: 100vh;
  display: flex;
  flex-direction: column;
  margin-bottom: 80px;
`

const InfoTitle = styled.div`
  text-align: center;
  margin: 5vh 0 3vh 0;
  font-size: 4rem;
  font-weight: 900;
`

const NavBlockWrapper = styled.div`
  position: fixed;
  bottom: 0;
  width: 100%;
  background-color: #ffffff; 
  z-index: 999; 
`;

export default function InfoTemp() {
  return (
    <InfoMain>
      <InfoTitle>용기 정보</InfoTitle>
      <div>
        <InfoForm/>
        <InfoForm/>
        <InfoForm/>
        <InfoForm/>
      </div>
      
      <NavBlockWrapper>
        <NavBlock />
      </NavBlockWrapper>
    </InfoMain>
  );
}
