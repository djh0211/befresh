import Paper from '@mui/material/Paper';
import { styled } from '@mui/material/styles';
import Typography from '@mui/material/Typography';
import AlarmContentIcon from '../atoms/alarmContentIcon';
import { useEffect, useState } from 'react';
import { Button } from '@mui/material';

type propsType = {
  id: string,
  type:string, 
  title: string,
  content:string, 
  deleteOne: (id :string) => void
}

const Item = styled(Paper)(() => ({
  backgroundColor:'#ffffff',
  display: 'flex',
  alignItems: 'center',
}));

export default function AlarmBlock({id, type, title, content, deleteOne}:propsType) {
  const [startX, setStartX] = useState<number>(0)
  const [endX, setEndX] = useState<number>(0)
  const [open, setOpen] = useState<boolean>(false)

  const handleDown = (e:React.MouseEvent<HTMLElement>|React.TouchEvent<HTMLElement>) => {
    if ('touches' in e) {
      if (typeof e.touches[0].clientX === 'number'){
        setStartX(e.touches[0].clientX)
      }
    } else{
      setStartX(e.clientX)
    }
  }
  const handleEnd = (e:React.MouseEvent<HTMLElement>|React.TouchEvent<HTMLElement>) => {
    if ('touches' in e) {
      if (typeof e.changedTouches[0].clientX === 'number') {
        setEndX(e.changedTouches[0].clientX)
      }
    } else {
      setEndX(e.clientX)
    }
  }

  useEffect(() => {
    if (endX - startX > 250) {
      setOpen(false)
    } else if (startX - endX > 250) {
      setOpen(true)
    }
  }, [endX])

  return (
    <div style={{display:'flex'}}>
      <Item 
        onMouseDown={handleDown}
        onTouchStart={handleDown}
        onMouseUp={handleEnd}
        onTouchEnd={handleEnd}
        sx={{display:'flex', width:'94%', ml:'3%', mb: 4, p: 4}} 
      >
        <AlarmContentIcon type={type}/>
        <div style={{width: '80%', marginLeft:'13px', marginRight:'5px', userSelect:'none'}}>
          <Typography noWrap sx={{fontSize:'2rem', fontWeight:900}}>{title}</Typography>
          <div>
            <Typography sx={{fontSize:'1.5rem'}}>{content}</Typography>
          </div>
        </div>
      </Item>
      {
        open && 
        <Button 
          onClick={() => {
            deleteOne(id)
            setOpen(false)
          }}
          sx={{
            width: '15%',
            mr: '3%', 
            mb: 4, 
            backgroundColor:'#fa785e',
            fontSize:'2em',
            color:'white'
          }}
        >
          삭제
        </Button>
      }
    </div>
    
  );
}
