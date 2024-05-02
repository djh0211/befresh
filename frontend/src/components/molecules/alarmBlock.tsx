import Paper from '@mui/material/Paper';
import { styled } from '@mui/material/styles';
import Typography from '@mui/material/Typography';
import AlarmContentIcon from '../atoms/alarmContentIcon';

const Item = styled(Paper)(() => ({
  backgroundColor:'#ffffff',
  textAlign: 'center',
  width:'90%',
  height: '7%',
  display: 'flex',
  alignItems: 'center'
}));


export default function AlarmBlock({type, content}: {type:string, content:string}) {
  return (
    <Item sx={{mb: 2, mx: 'auto', p: 2}}>
      <AlarmContentIcon type={type}/>
      <Typography noWrap sx={{fontSize:'1.5rem', marginLeft:'5px'}}>{content}</Typography>
    </Item>
  );
}
