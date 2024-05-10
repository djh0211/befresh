// import React, { useState, useEffect } from 'react';
// import ImgMediaCard from '../molecules/foodCard';
// import Grid from '@mui/material/Grid';
// import styled from 'styled-components';
// import { FoodTypes } from '../../types/foodTypes';

// interface CardFormProps {
//   cardApiData: FoodTypes[];
//   setData: React.Dispatch<React.SetStateAction<FoodTypes[]>>
// }

// const StyledGridContainer = styled(Grid)`
//   && {
//     margin-x: auto;
//     padding-left: 20px;
//     padding-right: 20px;
//     padding-top: 60px;
//   }
// `;

// export default function CardForm({ cardApiData, setData }: CardFormProps) {

//   const [cardData, setCardData] = useState(cardApiData);
//   const [flag, setFlag] = useState<boolean>(false);
//   console.log("삭제 작동?",cardApiData)
//   console.log(cardData,'필터전데이터?')

//   const handleDelete = (id: number) => {
//     // 삭제할 음식 카드를 제외한 새로운 데이터 배열 생성
//     console.log(id,'id?')
//     const newData = cardData.filter(food => food.id !== id);
//     console.log(cardData,'데이터?')
//     console.log(newData,'뉴데이터?')
//     // 새로운 데이터로 상태 업데이트
//     setFlag(true)
//     setCardData(newData);
//   };

//   useEffect(() => {
//     if (!flag) {
//       setCardData(cardApiData);
//     }
//   },[]);
  
//   return (
//     <StyledGridContainer container spacing={2}>
//       {cardData.map((foodData, index) => (
//         <Grid item key={index} xs={4} style={{ paddingBottom: '100px' }}>
//           <ImgMediaCard foodData={foodData} cardApiData={cardData} setData={setCardData} onDelete={handleDelete}/>
//         </Grid>
//       ))}
//     </StyledGridContainer>
//   );
// }



import React, { useState, useEffect } from 'react';
import ImgMediaCard from '../molecules/foodCard';
import Grid from '@mui/material/Grid';
import styled from 'styled-components';
import { FoodTypes } from '../../types/foodTypes';

interface CardFormProps {
  cardApiData: FoodTypes[];
  setData: React.Dispatch<React.SetStateAction<FoodTypes[]>>
}

const StyledGridContainer = styled(Grid)`
  && {
    margin-x: auto;
    padding-left: 20px;
    padding-right: 20px;
    padding-top: 60px;
  }
`;

export default function CardForm({ cardApiData, setData }: CardFormProps) {

  const handleDelete = (id: number) => {
    // 삭제할 음식 카드를 제외한 새로운 데이터 배열 생성
    console.log(id,'id?')
    setData(cardApiData.filter(food => food.id !== id));
  };

  useEffect(() => {
  },[]);
  
  return (
    <StyledGridContainer container spacing={2}>
      {cardApiData.map((foodData, index) => (
        <Grid item key={index} xs={4} style={{ paddingBottom: '100px' }}>
          <ImgMediaCard foodData={foodData} cardApiData={cardApiData} setData={setData} onDelete={handleDelete}/>
        </Grid>
      ))}
    </StyledGridContainer>
  );
}