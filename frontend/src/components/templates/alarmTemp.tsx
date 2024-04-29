import AlarmBlock from '../molecules/alarmBlock';
import NavBlock from '../molecules/navBlock';
import { ApiDataItem } from '../../types/types';

interface AlarmTemplateProps {
  
}

export default function AlarmTemplate() {
  return (
    <div>
      <main>
        <AlarmBlock />
        <NavBlock />
      </main>
    </div>
  );
}
