import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { styled } from '@mui/material/styles'; // styled 함수 import
import PropTypes from 'prop-types'; // prop-types import

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

interface PasswordInputTextFieldsProps {
  label: string; // label props의 타입을 명시적으로 string으로 지정
}

function PasswordInputTextFields({ label }: Readonly<PasswordInputTextFieldsProps>) { // label props를 받음
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
          label={label} // props로 받은 label을 사용
          type="password"
          autoComplete="current-password"
          variant="standard"
        />
      </div>
    </Box>
  );
}

PasswordInputTextFields.propTypes = {
  label: PropTypes.string.isRequired, // label은 문자열이어야 함을 정의
};

export default PasswordInputTextFields;
