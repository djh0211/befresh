import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import { Button } from "@mui/material";
import CardMedia from "@mui/material/CardMedia";
import Typography from "@mui/material/Typography";
import ProgressBar from "../atoms/progerssBar";
import BasicModal from "../../pages/modal";
import sampleimg from "../../assets/sampleimg.png";
import { FoodData, FoodTypes } from "../../types/foodTypes"; // FoodData 타입을 import합니다.
import { deleteFood } from "../../api/food/foodCardApi";

interface CardFormProps {
  foodData: FoodData;
  cardApiData: FoodTypes[];
  setData: React.Dispatch<React.SetStateAction<FoodTypes[]>>;
  onDelete: (id: number) => void;
}

export default function ImgMediaCard({
  foodData,
  setData,
  cardApiData,
  onDelete,
}: Readonly<CardFormProps>) {
  const { id, name, elapsedTime, image } = foodData;

  const newimage = image != null ? image.replace(/\\/g, "") : sampleimg;
  // 등록일시를 Date 객체로 변환합니다.
  const regDttmDate = new Date(foodData.regDttm);
  // 원하는 형식으로 날짜를 변환합니다.
  const formattedRegDttm = `${regDttmDate.getFullYear()}년 ${regDttmDate.getMonth() + 1}월 ${regDttmDate.getDate()}일`;

  const handleDelete = async () => {
    try {
      await deleteFood(id); // 해당 음식의 ID를 사용하여 삭제 요청을 보냅니다.
      onDelete(id); // onDelete 함수를 호출하여 해당 음식 카드를 삭제합니다.
    } catch (error) {
      console.error('음식 삭제 중 오류 발생:', error);
    }
  };
  return (
    <Card sx={{ maxWidth: 345 }}>
      <CardMedia
        component="img"
        alt={name}
        height="200"
        image={newimage ?? sampleimg}
      />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          {name}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {`${formattedRegDttm}부터 보관중`}
        </Typography>
        <ProgressBar value={foodData.freshState} />
        <Typography
          variant="body2"
          color="text.secondary"
          align="center"
          sx={{ marginTop: "10px" }}
        >
          {`${foodData.elapsedTime}일 경과`}
        </Typography>
      </CardContent>
      <CardActions sx={{ justifyContent: 'space-between' }}>
        <BasicModal
          foodData={foodData}
          cardApiData={cardApiData}
          setData={setData}
        />
      <Button variant="outlined" color="error" onClick={handleDelete} sx={{ fontWeight: 'bold' }}>
        음식 삭제
      </Button>
      </CardActions>
    </Card>
  );
}
