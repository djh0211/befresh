import NavBlock from "../molecules/navBlock";
import styled from "styled-components";
import InfoForm from "../organisms/InfoForm";
import { informationType } from "../../types/informationTypes";

const InfoMain = styled.div`
  width: 100vw;
  height: 80vh;
  display: flex;
  flex-direction: column;
  margin-bottom: 80px;
  overflow: scroll;
`

const Activelen = styled.p`
  text-align: right;
  width: 90vw;
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

const MessageDiv = styled.div`
  width: 90vw;
  background-color: rgba(161, 193, 167, 0.2);
  margin: 5%;
  height: 20vh;
  text-align: center;
`

export default function InfoTemp({ containerInfo }: Readonly<{ containerInfo: informationType[] }>) {
  return (
    <>
      <InfoTitle>용기 정보</InfoTitle>
      <Activelen>현재 사용중인 용기 {containerInfo.length}/10</Activelen>
      <InfoMain>
        {
          containerInfo.length == 0 ? (
            <MessageDiv>
              <p style={{ marginTop: '10%', fontSize: '1.5rem', color: 'grey' }}>등록된 용기가 없습니다.</p>
            </MessageDiv>
          ) : (
            <div>
              {
                containerInfo.map((info, idx) => {
                  return (
                    <InfoForm key={idx} information={info} />
                  )
                })
              }
            </div>
          )
        }
      </InfoMain>
      <NavBlockWrapper>
        <NavBlock />
      </NavBlockWrapper>
    </>

  );
}
