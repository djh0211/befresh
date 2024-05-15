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
  const [dangerCheck, setDangerCheck] = useState<boolean>(alarms.categories.includes('danger'))
  const [warnCheck, setWarnCheck] = useState<boolean>(alarms.categories.includes('warn'))
  const [registerCheck, setRegisterCheck] = useState<boolean>(alarms.categories.includes('register'))

  const handleDanger = (event: React.ChangeEvent<HTMLInputElement>) => {
    setDangerCheck(event.target.checked);
  };

  const handleWarn = (event: React.ChangeEvent<HTMLInputElement>) => {
    setWarnCheck(event.target.checked);
  };

  const handleRegister = (event: React.ChangeEvent<HTMLInputElement>) => {
    setRegisterCheck(event.target.checked);
  };

  // 설정할때마다 redux로 보내줌
  const settingCategory = () => {
    const temp:('danger'|'warn'|'register'|'error'|'noUpdate'|'reUpdate')[]  = ['error', 'noUpdate', 'reUpdate']
    if (dangerCheck) {
      temp.push('danger')
    }
    if (warnCheck) {
      temp.push('warn')
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
      <p style={{fontSize:'2rem', textAlign:'center', margin:'2vh 0'}}>알림 수신 설정</p>
      <FormGroup sx={{marginLeft: '20px'}}>
        <FormControlLabel control={<Checkbox checked={dangerCheck} onChange={handleDanger}/>} label="상한 음식"/>
        <FormControlLabel control={<Checkbox checked={warnCheck} onChange={handleWarn}/>} label="유통기한 경고" />
        <FormControlLabel control={<Checkbox checked={registerCheck} onChange={handleRegister}/>} label="새로운 음식 등록" />
      </FormGroup>
      <Button 
        variant="outlined" 
        size="large" 
        onClick={settingCategory}
        sx={{marginLeft:'70%'}}>
        설정 완료
      </Button>
    </>
  )
}