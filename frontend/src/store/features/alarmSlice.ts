import {createSlice, PayloadAction } from '@reduxjs/toolkit'
import { alarmType } from '../../types/alarmTypes'

type alarmsType = {
  alarms: alarmType[],
  alert : boolean
}

const initialAlarm: alarmsType = {
  alarms: [],
  alert: false
}

const alarmSlice = createSlice({
  name: 'alarms',
  initialState: initialAlarm,
  reducers: {
    // 알람 추가하는 함수
    addAlarm(state, action: PayloadAction<alarmType>) {
      state.alarms = [...state.alarms, action.payload]
    },
    // 알람 한개만 없애는 함수
    deleteOneAlarm(state, action:PayloadAction<string>){
      state.alarms = state.alarms.filter((alarm) => {
        return alarm.messageId != action.payload
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
    }
  }
})

export const { addAlarm, deleteOneAlarm, deleteAllAlarms, alertOn, alertOff } = alarmSlice.actions
export {alarmSlice}