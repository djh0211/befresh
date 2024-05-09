import CardForm from '../organisms/cardForm';
import NavBlock from '../molecules/navBlock';
import { FoodTypes } from '../../types/foodTypes';
import styled from 'styled-components';

interface MainPageTemplateProps {
  cardApiData: FoodTypes[];
  setData:React.Dispatch<React.SetStateAction<FoodTypes[]>>
}

const Main = styled.div`
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`

const NavBlockWrapper = styled.div`
  position: fixed;
  bottom: 0;
  width: 100%;
  background-color: #ffffff; 
  z-index: 999; 
`;

export default function MainPageTemplate({ cardApiData, setData }: Readonly<MainPageTemplateProps>) {
  return (
    <div>
      <Main>
        <CardForm cardApiData={cardApiData} setData={setData} />
      </Main>
      <NavBlockWrapper>
        <NavBlock />
      </NavBlockWrapper>
    </div>
  );
}