import React, {useState, useEffect } from 'react';
import ImgMediaCard from '../molecules/foodCard';
import Grid from '@mui/material/Grid';
import styled from 'styled-components';
import { FoodTypes } from '../../types/foodTypes';
import emptyimg1 from '../../assets/empty1.png'
import emptyimg2 from '../../assets/empty2.png'

interface CardFormProps {
  cardApiData: FoodTypes[];
  setData: React.Dispatch<React.SetStateAction<FoodTypes[]>>
}

const StyledCenterContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
`;

const StyledGridContainer = styled(Grid)`
  && {
    margin-x: auto;
    padding-left: 20px;
    padding-right: 20px;
    padding-top: 60px;
  }
`;
const imageList = [
  emptyimg1,
  emptyimg2,
];

const randomIndex = Math.floor(Math.random() * imageList.length);

const emptyimg = imageList[randomIndex]

export default function CardForm({ cardApiData, setData }: Readonly<CardFormProps>) {

  useEffect(() => {
  }, []); // 컴포넌트가 마운트될 때 한 번만 실행

  const handleDelete = (id: number) => {
    setData(cardApiData.filter(food => food.id !== id));
  };

  return (
    <StyledCenterContainer>
      {cardApiData.length === 0 ? (
        <React.Fragment>
          <img src={emptyimg} alt="빈 냉장고 이미지" style={{ maxWidth: "20%", maxHeight: "20%" }} />
          <p>냉장고 안에 아무런 음식이 없어요</p>
        </React.Fragment>
      ) : (
        <StyledGridContainer container spacing={2}>
          {cardApiData.map((foodData, index) => (
            <Grid item key={index} xs={4} style={{ paddingBottom: '100px' }}>
              <ImgMediaCard foodData={foodData} cardApiData={cardApiData} setData={setData} onDelete={handleDelete}/>
            </Grid>
          ))}
        </StyledGridContainer>
      )}
    </StyledCenterContainer>
  );
}