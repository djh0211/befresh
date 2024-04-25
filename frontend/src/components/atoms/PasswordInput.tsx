import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { styled } from '@mui/material/styles'; // styled 함수 import

// TextField 컴포넌트를 상속하여 새로운 스타일을 적용하는 컴포넌트 생성
const CustomTextField = styled(TextField)({
  '& .MuiInputLabel-root': {
    '&.Mui-focused': { // TextField가 focus되었을 때 라벨 색상
      color: 'green', // focus 시 라벨 색상 변경
    },
  },
  '& .MuiInputBase-root': {
    '&:after': { // TextField가 focus되었을 때 하단 선 스타일
      borderBottomColor: 'green', // 클릭 시 하단 선 색상 변경
    },
  },
});

function PasswordInputTextFields() {
  return (
    <Box
      component="form"
      sx={{
        '& .MuiTextField-root': { m: 1, width: '25ch' },
      }}
      noValidate
      autoComplete="off"
    >
      <div>
        <CustomTextField
          id="standard-password-input"
          label="Password"
          type="password"
          autoComplete="current-password"
          variant="standard"
        />
      </div>
    </Box>
  );
}

export default PasswordInputTextFields