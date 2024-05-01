import { styled } from '@mui/material/styles';
import TextField from '@mui/material/TextField';

// TextField 컴포넌트를 상속하여 새로운 스타일을 적용하는 컴포넌트 생성
const CustomTextField = styled(TextField)({
  width: '200px', 
  '& .MuiInputLabel-root': {
    '&.Mui-focused': { 
      color: 'green', 
    },
  },
  '& .MuiInputBase-root': {
    '&:after': { 
      borderBottomColor: 'green', 
    },
  },
});

interface IdInputTextFieldsProps {
  onChange: (event: React.ChangeEvent<HTMLInputElement>) => void; // 변경 이벤트 핸들러를 받을 props
}

function IdInputTextFields({ onChange }: Readonly<IdInputTextFieldsProps>) {
  return (
    <CustomTextField
      id="standard-basic"
      label="ID"
      variant="standard"
      onChange={onChange} 
    />
  );
}

export default IdInputTextFields;
