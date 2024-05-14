import { styled } from '@mui/material/styles';
import TextField from '@mui/material/TextField';

const CustomTextField = styled(TextField)({
  width:'300px',
  height:'150px',
  '& .MuiInputBase-input': {
    fontSize: '16px',
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
