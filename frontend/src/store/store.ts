import { combineReducers, configureStore } from '@reduxjs/toolkit'
// import refReducer from './features/refIdSlice'
import storageSession from 'redux-persist/lib/storage/session'
import { alarmSlice } from './features/alarmSlice'
import { persistStore, persistReducer } from 'redux-persist'


const persistConfig = {
  key: 'root',
  storage: storageSession,
  // 로컬에 저장하고 싶은 애만 빼주기
  whitelist: ['alarms']
}

const persistedReducer = persistReducer(
  persistConfig,
  combineReducers({
    alarms: alarmSlice.reducer
  })
)

const store = configureStore({
  reducer: persistedReducer,
  middleware: getDefaultMiddleware => getDefaultMiddleware({serializableCheck:false})
})

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
export type AppStore = typeof store;

export const persistor = persistStore(store)
export default store;