// 카드 관련 API 호출 함수 정의
export async function fetchCardData() {
  try {
    const response = await fetch('카드 관련 API 주소');
    const data = await response.json();
    return data;
  } catch (error) {
    console.error('카드 데이터를 불러오는 중 오류 발생:', error);
    throw error;
  }
}

// 카드 생성 API 호출 함수 정의
export async function createCard(data: any) {
  try {
    const response = await fetch('카드 생성 API 주소', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    });
    const result = await response.json();
    return result;
  } catch (error) {
    console.error('카드 생성 중 오류 발생:', error);
    throw error;
  }
}

// 다른 카드 관련 API 호출 함수들도 추가 가능
