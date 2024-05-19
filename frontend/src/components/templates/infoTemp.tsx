import React, { useEffect, useState } from 'react';
import NavBlock from "../molecules/navBlock";
import styled from "styled-components";
import InfoForm from "../organisms/InfoForm";
import { informationType } from "../../types/informationTypes";
import { Button } from "@mui/material";

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
  background-color: #dceecd;
  margin: 5%;
  height: 20vh;
  text-align: center;
`

export default function InfoTemp({ containerInfo, showBackBtn }: Readonly<{ containerInfo: informationType[], showBackBtn: boolean }>) {
  const [activeContainerCount, setActiveContainerCount] = useState(0);

  useEffect(() => {
    const activeContainers = containerInfo.filter(info => info.refresh !== "데이터없음");
    setActiveContainerCount(activeContainers.length);
  }, [containerInfo]);

  return (
    <>
      <InfoTitle>용기 정보</InfoTitle>
      <Activelen>현재 사용중인 용기: {activeContainerCount}/{containerInfo.length}</Activelen>
      {
        showBackBtn === true && 
        <Button 
          variant="outlined" 
          size="large" 
          onClick={() => {window.location.reload()}}
          sx={{
            marginLeft:'5%', 
            fontSize:'1.5rem', 
            color:'black', 
            borderColor:'black'
          }}
        >
          전체보기
        </Button>
      }
      <InfoMain>
        {
          containerInfo.length === 0 ? (
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
