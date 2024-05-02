import MailIcon from '@mui/icons-material/Mail';
import { Badge } from '@mui/material';

export default function AlarmIcon() {
  const location :string = window.location.href
  return (
    <Badge badgeContent={3} color='success' variant="dot">
      <MailIcon color={location.includes('alarm') ? "success" : "action"} sx={{fontSize:'5rem'}}/>
    </Badge>
  );
}