import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Typography from "@mui/material/Typography";
import ProgressBar from "../atoms/progerssBar";
import BasicModal from "../../pages/modal";
import sampleimg from "../../assets/sampleimg.png";
import { FoodData,FoodTypes } from "../../types/foodTypes"; // FoodData 타입을 import합니다.

interface CardFormProps {
  foodData:FoodData
  cardApiData: FoodTypes[];
  setData: React.Dispatch<React.SetStateAction<FoodTypes[]>>
}


export default function ImgMediaCard({
  foodData, setData, cardApiData
}: Readonly<CardFormProps>) {
  const { name, elapsedTime, image } = foodData;
  console.log("내려옴?", foodData.image)

  const newimage = image != null ? image.replace(/\\/g, "") : sampleimg;
  // 등록일시를 Date 객체로 변환합니다.
  const regDttmDate = new Date(foodData.regDttm);
  // 원하는 형식으로 날짜를 변환합니다.
  const formattedRegDttm = `${regDttmDate.getFullYear()}년 ${regDttmDate.getMonth() + 1}월 ${regDttmDate.getDate()}일`;

  return (
    <Card sx={{ maxWidth: 345 }}>
      <CardMedia
        component="img"
        alt={name}
        height="140"
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
      <CardActions>
        <BasicModal foodData={foodData} cardApiData={cardApiData} setData={setData} />
      </CardActions>
    </Card>
  );
}