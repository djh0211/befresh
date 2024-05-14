import { Button, Checkbox, FormControlLabel, FormGroup  } from "@mui/material"
import { setCategories } from "../../store/features/alarmSlice"
import { useDispatch } from "react-redux"
import { useState, useEffect } from "react"
import { useSelector } from "react-redux"
import { RootState } from "../../store/store"

// 체크박스 받아와서 리덕스로 보내기
// 처음에 들어올때 체크 된거 가져오기

export default function AlarmSettingBlock({close}:{close :() => void}) {
  const dispatch = useDispatch()
  let alarms = useSelector((state:RootState) => state.alarms)
  // 각각 체크 된 거 표시, 바꾸기 위한 변수
  const [expireCheck, setExpireCheck] = useState<boolean>(alarms.categories.includes('expire'))
  const [refreshCheck, setRefreshCheck] = useState<boolean>(alarms.categories.includes('refresh'))
  const [registerCheck, setRegisterCheck] = useState<boolean>(alarms.categories.includes('register'))

  const handleExpire = (event: React.ChangeEvent<HTMLInputElement>) => {
    setExpireCheck(event.target.checked);
  };

  const handleRefresh = (event: React.ChangeEvent<HTMLInputElement>) => {
    setRefreshCheck(event.target.checked);
  };

  const handleRegister = (event: React.ChangeEvent<HTMLInputElement>) => {
    setRegisterCheck(event.target.checked);
  };


  // 설정할때마다 redux로 보내줌
  const settingCategory = () => {
    const temp:('expire'|'refresh'|'register')[]  = []
    if (expireCheck) {
      temp.push('expire')
    }
    if (refreshCheck) {
      temp.push('refresh')
    }
    if (registerCheck) {
      temp.push('register')
    }
    dispatch(setCategories(temp))
    close()
    window.location.reload()
  }

  return(
    <>
      <p style={{fontSize:'2rem', textAlign:'center', margin:'3vh 0'}}>알림 수신 설정</p>
      <FormGroup>
        <FormControlLabel control={<Checkbox checked={expireCheck} onChange={handleExpire}/>} label="음식 유통기한 알림"/>
        <FormControlLabel control={<Checkbox checked={refreshCheck} onChange={handleRefresh}/>} label="용기 상태 알림" />
        <FormControlLabel control={<Checkbox checked={registerCheck} onChange={handleRegister}/>} label="새로운 음식 등록" />
      </FormGroup>
      <Button 
        variant="outlined" 
        size="large" 
        onClick={settingCategory}
        sx={{width:'25%', marginLeft:'75%'}}>
        설정 완료
      </Button>
    </>
  )
}