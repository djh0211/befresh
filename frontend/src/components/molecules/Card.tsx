import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import ProgressBar from '../atoms/progerssBar';
import BasicModal from '../atoms/modal';
import samimg from '../../assets/sampleimg.png'

export default function ImgMediaCard({ progressValue }:Readonly<{ progressValue:number}>) { // progressValue props를 받아옴
  return (
    <Card sx={{ maxWidth: 345 }}>
      <CardMedia
        component="img"
        alt="green iguana"
        height="140"
        image="../../assets/sampleimg.png"
      />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          갈비찜
        </Typography>
        <Typography variant="body2" color="text.secondary">
          오늘부터 보관중
        </Typography>
        <ProgressBar value={progressValue} /> {/* progressValue를 ProgressBar 컴포넌트의 value props로 전달 */}
      </CardContent>
      <CardActions>
        <BasicModal />
      </CardActions>  
    </Card>
  );
}
