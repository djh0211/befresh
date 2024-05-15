import axiosInstance from '../../utils/axiosConfig';
import dayjs from 'dayjs';


// 음식 상세 정보를 가져오는 함수
export async function modalFoodDetail(foodId: number): Promise<any> {
  try {
    console.log('상세정보 시작')
    const response = await axiosInstance.get(`https://be-fresh.site/api/foods/detail?foodId=${foodId}`);
    console.log('모달 겟요청 보냄',response.data)
    return response.data;
  } catch (error) {
    console.log('모달 상세 정보 오류 :', error);
  }
}

export async function updateFoodDetail(updatedData: {
  foodId?: number;
  name?: string;
  expirationDate?: string;
}): Promise<void> {
  try {
    if (updatedData.expirationDate) {
      const newExpirationDate = dayjs(updatedData.expirationDate).add(9, 'hour').toISOString();
      updatedData.expirationDate = newExpirationDate;
    }

    await axiosInstance.put(`https://be-fresh.site/api/foods`, updatedData);
    console.log('수정됨?');
  } catch (error) {
    console.log('음식 정보 수정 오류 :', error);
  }
}