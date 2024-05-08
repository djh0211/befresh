import ImgMediaCard from '../molecules/foodCard';
import Grid from '@mui/material/Grid';
import styled from 'styled-components';
import { FoodTypes } from '../../types/foodTypes';
import { useEffect } from 'react';

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

export default function CardForm({ cardApiData, setData }: Readonly<CardFormProps>) {
  useEffect(()=>{
    console.log(cardApiData)
  },[])
  
  return (
    <StyledGridContainer container spacing={2}>
      {cardApiData.map((foodData, index) => (
        <Grid item key={index} xs={4} style={{ paddingBottom: '100px' }}>
          <ImgMediaCard foodData={foodData} cardApiData={cardApiData} setData={setData}/>
        </Grid>
      ))}
    </StyledGridContainer>
  );
}