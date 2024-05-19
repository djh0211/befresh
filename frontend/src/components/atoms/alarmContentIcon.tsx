import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import WarningAmberIcon from '@mui/icons-material/WarningAmber';
import ReportOutlinedIcon from '@mui/icons-material/ReportOutlined';
import DeleteOutlineOutlinedIcon from '@mui/icons-material/DeleteOutlineOutlined';
import SensorsOutlinedIcon from '@mui/icons-material/SensorsOutlined';
import SensorsOffOutlinedIcon from '@mui/icons-material/SensorsOffOutlined';

export default function AlarmContentIcon({type} : {type: string}) {
  if (type == 'warn') {
    return(
      <WarningAmberIcon color='warning' sx={{width:'80px', fontSize:'4rem'}}/>
    )
  } else if (type === 'register') {
    return(
      <AddCircleOutlineIcon color='success' sx={{width:'80px', fontSize:'4rem'}}/>
    )
  } else if (type === 'danger') {
    return(
      <DeleteOutlineOutlinedIcon color='error' sx={{width:'80px', fontSize:'4rem'}}/>
    )
  } else if (type === 'error') {
    // error의 경우
    return(
      <ReportOutlinedIcon color='error' sx={{width:'80px', fontSize:'4rem'}}/>
    ) 
  } else if (type === 'noUpdate') {
    return (
      <SensorsOffOutlinedIcon color='error' sx={{width:'80px', fontSize:'4rem'}}/>
    )
  } else {
    return(
      <SensorsOutlinedIcon sx={{width:'80px', fontSize:'4rem'}}/>
    )
  }
}