import { combineReducers, configureStore } from '@reduxjs/toolkit'
import refReducer from './features/refIdSlice'
import storageSession from 'redux-persist/lib/storage/session'

const persistConfig = {
  key: 'root',
  storage: storageSession,
  // 로컬에 저장하고 싶은 애만 빼주기
  whitelist: ['chat', 'member', 'accessToken', 'consultSlice', 'htpAnswer', 'htpSurveys', 'notificationToken']
}



export const store = configureStore({
  reducer:{


  },
})

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;

export default store;