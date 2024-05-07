import MainPageTemplate from "../components/templates/mainTemp";
import { useEffect, useState } from 'react';
import { getFoods } from '../api/food/foodCardApi';
import { FoodTypes } from "../types/foodTypes"; // 음식 데이터를 가져오는 API 함수 import

function MainPage() {
  const [cardData, setCardData] = useState<FoodTypes[]>([]); // 카드 데이터 상태 초기화

  useEffect(() => {
    // 음식 데이터를 가져오는 비동기 함수 호출
    const fetchFoodsData = async () => {
      try {
        const foodsData: FoodTypes[] = await getFoods(); // 음식 데이터 가져오기
        // 가져온 음식 데이터를 카드 데이터로 변환하여 상태에 저장
        console.log(foodsData)
        setCardData(foodsData);
      } catch (error) {
        console.error('음식 데이터를 가져오는 데 실패했습니다:', error);
        // 오류 처리
      }
    };

    // 컴포넌트가 마운트될 때 음식 데이터를 가져오도록 호출
    fetchFoodsData();
  }, []);
  useEffect(() => {
    console.log(cardData)
  }, [cardData])

  return (
    <div>
      {/* MainPageTemplate 컴포넌트에 카드 데이터를 props로 전달 */}
      {
        cardData === undefined ? (<p>냉장고 데이터를 가져오는 것을 실패!</p>) : (
          <MainPageTemplate cardApiData={cardData} /> 
        )
      }
    </div>
  );
}

export default MainPage;


// import MainPageTemplate from "../components/templates/mainTemp";
// import { useEffect, useState } from 'react';

// function MainPage() {
//   const [cardData, setCardData] = useState([{ id: 1, title: '카드 1', content: '카드 내용 1', progressValue: Math.floor(Math.random() * 101) }]);

  

//   useEffect(() => {
//     // 임시 카드 데이터 생성
//     const tempCardData = [
//       { id: 1, title: '카드 1', content: '카드 내용 1', progressValue: 30 },
//       { id: 2, title: '카드 2', content: '카드 내용 2', progressValue: 50 },
//       { id: 3, title: '카드 3', content: '카드 내용 3', progressValue: 15 }
//     ];
    
//     // 임시 데이터를 상태에 저장
//     setCardData(tempCardData);
//   }, []);

//   return (
//     <div>
//       <MainPageTemplate cardApiData={cardData} />
//     </div>
//   );
// }

// export default MainPage;
