import { createSlice, PayloadAction } from "@reduxjs/toolkit";

interface FoodIdType {
  value: number | null;
}

const initialFoodIdState: FoodIdType = {
  value: null
};

const foodIdSlice = createSlice({
  name: 'foodId',
  initialState: initialFoodIdState,
  reducers: {
    addFoodId: (state, action: PayloadAction<number>) => {
      state.value = action.payload;
    },
    deleteFoodId: (state) => {
      state.value = null
    }
  }
});

export const { addFoodId, deleteFoodId } = foodIdSlice.actions; // 액션 생성자 추출

export default foodIdSlice.reducer; // 리듀서 함수 추출
export {foodIdSlice}