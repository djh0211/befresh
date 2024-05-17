import { styled } from '@mui/material/styles';
import TextField from '@mui/material/TextField';

const CustomTextField = styled(TextField)({
  width:'40vw',
  height:'15vh',
  '& .MuiInputBase-input': {
    fontSize: '25px',
    width: '250px',
    height: '50px',
  },
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
  onChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
  onKeyDown: (event: React.KeyboardEvent<HTMLInputElement>) => void;
}

function IdInputTextFields({ onChange, onKeyDown }: Readonly<IdInputTextFieldsProps>) {
  return (
    <CustomTextField
      id="standard-basic"
      label="ID"
      variant="standard"
      onChange={onChange}
      onKeyDown={onKeyDown}
    />
  );
}

export default IdInputTextFields;
