// import MainPageTemplate from "../components/templates/mainTemplates";
// import { fetchCardData } from '../api/food/cardApi'; // 카드 관련 API 호출 함수 가져오기
// import { useEffect, useState } from 'react';

// function MainPage() {
//   const [cardData, setCardData] = useState([]);

//   useEffect(() => {
//     // 페이지 로드 시 카드 데이터를 불러와서 상태에 저장
//     async function fetchAndSetCardData() {
//       try {
//         const data = await fetchCardData();
//         setCardData(data);
//       } catch (error) {
//         console.error('카드 데이터를 불러오는 중 에러 발생:', error);
//       }
//     }

//     fetchAndSetCardData();
//   }, []);

//   return (
//     <div>
//       <MainPageTemplate cardApiData={cardData} />
//     </div>
//   );
// }

// export default MainPage;

import MainPageTemplate from "../components/templates/mainTemplates";
import { useEffect, useState } from 'react';

function MainPage() {
  const [cardData, setCardData] = useState([{ id: 1, title: '카드 1', content: '카드 내용 1', progressValue: Math.floor(Math.random() * 101) }]);

  

  useEffect(() => {
    // 임시 카드 데이터 생성
    const tempCardData = [
      { id: 1, title: '카드 1', content: '카드 내용 1', progressValue: 30 },
      { id: 2, title: '카드 2', content: '카드 내용 2', progressValue: 50 },
      { id: 3, title: '카드 3', content: '카드 내용 3', progressValue: 15 }
    ];
    
    // 임시 데이터를 상태에 저장
    setCardData(tempCardData);
  }, []);

  return (
    <div>
      <MainPageTemplate cardApiData={cardData} />
    </div>
  );
}

export default MainPage;
