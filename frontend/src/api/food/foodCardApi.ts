import axiosInstance from '../../utils/axiosConfig';


// 음식 데이터 가져오기
export async function getFoods() {
  // const accessToken = getAccessToken()
  try {
    console.log('메인페이지 열면서 음식가져오기')
    const response = await axiosInstance.get('https://be-fresh.site/api/foods',{
      // headers: {
      //   'Authorization': `Bearer ${accessToken}`
      // }
    });
    console.log('메인 겟요청 보내기')
    return response.data.result;
  } catch (error) {
    console.error('음식 실패:', error);
  }
}

export async function deleteFood(foodId: number) {
  try {
    const response = await axiosInstance.delete(`https://be-fresh.site/api/foods?foodId=${foodId}`);
    if (response.status === 200) {
      console.log('음식 삭제 성공');
      // 삭제 성공 시 추가적인 작업을 수행할 수 있습니다.
    } else {
      console.error('음식 삭제 실패');
      // 삭제 실패 시 에러 처리를 수행할 수 있습니다.
    }
  } catch (error) {
    console.error('음식 삭제 중 오류 발생:', error);
    // 오류 발생 시 에러 처리를 수행할 수 있습니다.
  }
}