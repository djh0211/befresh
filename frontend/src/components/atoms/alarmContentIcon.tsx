import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import WarningAmberIcon from '@mui/icons-material/WarningAmber';


export default function AlarmContentIcon({type} : {type: string}) {
  if (type == 'warning') {
    return(
      <WarningAmberIcon color='warning' sx={{width:'10%', fontSize:'4rem'}}/>
    )
  } else {
    return(
      <AddCircleOutlineIcon color='success' sx={{width:'10%', fontSize:'4rem'}}/>
    )
  }
}