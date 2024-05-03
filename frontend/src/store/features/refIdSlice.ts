import { createSlice, PayloadAction } from "@reduxjs/toolkit";

interface RefIdState {
  refId: string;
}

const initialRefIdState: RefIdState = {
  refId: ""
};

const refIdSlice = createSlice({
  name: 'refId',
  initialState: initialRefIdState,
  reducers: {
    setRefId: (state, action: PayloadAction<string>) => {
      state.refId = action.payload;
    }
  }
});

export const { setRefId } = refIdSlice.actions; // 액션 생성자 추출

export default refIdSlice.reducer; // 리듀서 함수 추출
