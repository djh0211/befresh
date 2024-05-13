import {createSlice, PayloadAction } from '@reduxjs/toolkit'
import { alarmType } from '../../types/alarmTypes'

type alarmsType = {
  alarms: alarmType[],
  alert : boolean,
  categories: ('expire'|'refresh'|'register')[] 
}

const initialAlarm: alarmsType = {
  alarms: [],
  alert: false,
  categories: ['expire', 'refresh', 'register']
}

const alarmSlice = createSlice({
  name: 'alarms',
  initialState: initialAlarm,
  reducers: {
    // 알람 한개만 추가하는 함수
    addAlarm(state, action: PayloadAction<alarmType>) {
      state.alarms = [action.payload, ...state.alarms]
    },
    // 알람 여러개 추가
    addAlarms(state, action: PayloadAction<alarmType[]>) {
      state.alarms = [...state.alarms, ...action.payload]
    },
    // 알람 한개만 없애는 함수(notificationId 기준)
    deleteOneAlarm(state, action:PayloadAction<string>){
      state.alarms = state.alarms.filter((alarm) => {
        return alarm.data.notificationId != action.payload
      })
    },
    // 알람 다 없애주는 함수
    deleteAllAlarms(state) {
      state.alarms = []
    },
    // 알림 on
    alertOn(state) {
      state.alert = true
    },
    // 알림 off
    alertOff(state) {
      state.alert = false
    },
    // 보려는 카테고리 설정
    setCategories(state, action:PayloadAction<('expire'|'refresh'|'register')[] >) {
      state.categories = [...action.payload]
    }
  }
})

export const { addAlarm, addAlarms, deleteOneAlarm, deleteAllAlarms, alertOn, alertOff, setCategories } = alarmSlice.actions
export {alarmSlice}