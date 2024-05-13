import { Button, Checkbox, FormControlLabel, FormGroup  } from "@mui/material"
import styled from "styled-components"
import { setCategories } from "../../store/features/alarmSlice"
import { useDispatch } from "react-redux"
import { useEffect, useState } from "react"


// 체크박스 받아와서 리덕스로 보내기
// 처음에 들어올때 체크 된거 가져오기

export default function AlarmSettingBlock() {
  const dispatch = useDispatch()

  const ModalTitle = styled.p`
    font-size: 2rem;
    text-align: center;
    margin: 3vh 0 3vh 0;
  `
  const [expireCheck, setExpireCheck] = useState<boolean>(true)
  const [refreshCheck, setRefreshCheck] = useState<boolean>(true)
  const [RegisterCheck, setRegisterCheck] = useState<boolean>(true)

  const handleExpire = (event: React.ChangeEvent<HTMLInputElement>) => {
    setExpireCheck(event.target.checked);
  };

  const handleRefresh = (event: React.ChangeEvent<HTMLInputElement>) => {
    setRefreshCheck(event.target.checked);
  };

  const handleRegister = (event: React.ChangeEvent<HTMLInputElement>) => {
    setRegisterCheck(event.target.checked);
  };

  return(
    <>
      <ModalTitle>알림 수신 설정</ModalTitle>
      <FormGroup>
        <FormControlLabel control={<Checkbox checked={expireCheck} onChange={handleExpire}/>} label="음식 유통기한 알림"/>
        <FormControlLabel control={<Checkbox checked={refreshCheck} onChange={handleRefresh}/>} label="용기 상태 알림" />
        <FormControlLabel control={<Checkbox checked={RegisterCheck} onChange={handleRegister}/>} label="새로운 음식 등록" />
      </FormGroup>
      <Button variant="outlined" size="large" sx={{width:'25%', marginLeft:'75%'}}>
        설정 완료
      </Button>
    </>
  )
}