import ImgMediaCard from '../molecules/Card';
import Stack from '@mui/material/Stack';
import { ApiDataItem } from '../../types/types'; // 타입 가져오기

interface CardFormProps {
  cardApiData: ApiDataItem[];
}

export default function CardForm({ cardApiData }: CardFormProps) {
  return (
    <Stack spacing={2}>
      {cardApiData.map((item, index) => (
        <ImgMediaCard key={index} progressValue={item.progressValue} />
      ))}
    </Stack>
  );
}
