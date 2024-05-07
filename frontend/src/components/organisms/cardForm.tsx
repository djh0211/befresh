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
    margin-top: 60px;
    padding-left: 20px;
    padding-right: 20px;
    padding-top: 60px;
  }
`;

export default function CardForm({ cardApiData }: Readonly<CardFormProps>) {
  useEffect(()=>{
    console.log(cardApiData)
  },[])
  
  return (
    <StyledGridContainer container spacing={2}>
      {cardApiData.map((foodData, index) => (
        <Grid item key={index} xs={4} style={{ paddingBottom: '100px' }}>
          <ImgMediaCard foodData={foodData} />
        </Grid>
      ))}
    </StyledGridContainer>
  );
}
