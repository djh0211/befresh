import CardForm from '../organisms/cardForm';
import NavBlock from '../molecules/navBlock';
import { ApiDataItem } from '../../types/types';

interface MainPageTemplateProps {
  cardApiData: ApiDataItem[];
}

export default function MainPageTemplate({ cardApiData }: MainPageTemplateProps) {
  return (
    <div>
      <main>
        <CardForm cardApiData={cardApiData} />
        <NavBlock />
      </main>
    </div>
  );
}
