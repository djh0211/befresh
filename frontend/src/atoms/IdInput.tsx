import { styled } from '@mui/material/styles'; // styled 함수 import
import TextField from '@mui/material/TextField';

// TextField 컴포넌트를 상속하여 새로운 스타일을 적용하는 컴포넌트 생성
const CustomTextField = styled(TextField)({
  width: '200px', // 가로 길이를 최대한 확보하기 위해 100%로 설정
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

function IdInputTextFields() {
  return (
    <CustomTextField id="standard-basic" label="ID" variant="standard" />
  );
}

export default IdInputTextFields;
