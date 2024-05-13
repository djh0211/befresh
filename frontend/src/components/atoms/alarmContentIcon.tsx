import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import WarningAmberIcon from '@mui/icons-material/WarningAmber';
import ReportOutlinedIcon from '@mui/icons-material/ReportOutlined';


export default function AlarmContentIcon({type} : {type: string}) {
  if (type == 'expire') {
    return(
      <WarningAmberIcon color='warning' sx={{width:'100px', fontSize:'4rem'}}/>
    )
  } else if (type === 'register') {
    return(
      <AddCircleOutlineIcon color='success' sx={{width:'100px', fontSize:'4rem'}}/>
    )
  } else {
    return(
      <ReportOutlinedIcon color='error' sx={{width:'100px', fontSize:'4rem'}}/>
    )
  }
}