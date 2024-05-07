import ImgMediaCard from '../molecules/foodCard';
import Grid from '@mui/material/Grid';
import styled from 'styled-components';
import { FoodTypes } from '../../types/foodTypes';
import { useEffect } from 'react';

interface CardFormProps {
  cardApiData: FoodTypes[];
}

const StyledGridContainer = styled(Grid)`
  && {
    margin-top: 20px;
    padding-left: 20px;
    padding-right: 20px;
  }
`;

export default function CardForm({ cardApiData }: Readonly<CardFormProps>) {
  useEffect(()=>{
    console.log(cardApiData)
  },[])
  
  return (
    <StyledGridContainer container spacing={2}>
      {cardApiData.map((foodData, index) => (
        <Grid item key={index} xs={4}>
          <ImgMediaCard foodData={foodData} />
        </Grid>
      ))}
    </StyledGridContainer>
  );
}
