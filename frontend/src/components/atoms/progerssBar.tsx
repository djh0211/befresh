import { styled } from '@mui/material/styles';
import Stack from '@mui/material/Stack';
import LinearProgress, { linearProgressClasses } from '@mui/material/LinearProgress';
// import PropTypes from 'prop-types'; // prop-types import

// LinearProgress 컴포넌트를 상속하여 새로운 스타일을 적용하는 컴포넌트 생성
const BorderLinearProgress = styled(LinearProgress)(({ value }: { value: number }) => ({
  height: 10,
  borderRadius: 5,
  [`&.${linearProgressClasses.colorPrimary}`]: {
    backgroundColor: value >= 50 ? '#1a90ff' : (value >= 25 ? '#ffeb3b' : '#f44336'), // 50 이상일 때 파란색, 25 이상일 때 노란색, 미만일 때 빨간색
  },
  [`& .${linearProgressClasses.bar}`]: {
    borderRadius: 5,
    backgroundColor: value >= 50 ? '#1a90ff' : (value >= 25 ? '#ffeb3b' : '#f44336'), // 50 이상일 때 파란색, 25 이상일 때 노란색, 미만일 때 빨간색
  },
}));

function ProgressBar({ value }: { value: number }) {
  return (
    <Stack spacing={2} sx={{ flexGrow: 1 }}>
      <br />
      <BorderLinearProgress variant="determinate" value={value} />
    </Stack>
  );
}

// CustomizedProgressBars.propTypes = {
//   value: PropTypes.number.isRequired, // value는 숫자여야 함을 정의
// };

export default ProgressBar;
