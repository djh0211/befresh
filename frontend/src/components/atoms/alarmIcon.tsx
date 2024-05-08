import MailIcon from '@mui/icons-material/Mail';
import { Badge } from '@mui/material';
import { useSelector } from 'react-redux';
import { RootState } from '../../store/store';

export default function AlarmIcon() {
  const location :string = window.location.href
  const alarms = useSelector((state:RootState) => state.alarms)
  
  return (
    <Badge badgeContent={alarms.alert ? 1 : 0} color='success' variant="dot">
      <MailIcon color={location.includes('alarm') ? "success" : "action"} sx={{fontSize:'5rem'}}/>
    </Badge>
  );
}