import CardForm from '../organisms/cardForm';
import NavBlock from '../molecules/navBlock';
import { FoodTypes } from '../../types/foodTypes';
import styled from 'styled-components';

interface MainPageTemplateProps {
  cardApiData: FoodTypes[];
}

const Main = styled.div`
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`

export default function MainPageTemplate({ cardApiData }: Readonly<MainPageTemplateProps>) {
  return (
    <div>
      <Main>
        <CardForm cardApiData={cardApiData} />
        <NavBlock />
      </Main>
    </div>
  );
}
