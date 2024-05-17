import { styled } from '@mui/material/styles';
import Stack from '@mui/material/Stack';
import LinearProgress, { linearProgressClasses } from '@mui/material/LinearProgress';

// 프로그레스 바의 색상을 결정하는 함수
function determineBackgroundColor(value:number) {
  if (value <= 30) {
    return '#f44336';
  } else if (value <= 60) {
    return '#ffeb3b';
  } else {
    return '#4caf50';
  }
}

// 스타일을 적용한 프로그레스 바 컴포넌트
const BorderLinearProgress = styled(LinearProgress)(({ value }:{value:number}) => ({
  height: 10,
  borderRadius: 5,
  [`&.${linearProgressClasses.colorPrimary}`]: {
    backgroundColor: '#c7c7c7',
  },
  [`& .${linearProgressClasses.bar}`]: {
    borderRadius: 5,
    backgroundColor: determineBackgroundColor(value),
  },
}));

// 프로그레스 바 컴포넌트
function ProgressBar({ value }: Readonly<{value:number}>) {
  return (
    <Stack spacing={2} sx={{ flexGrow: 1 }}>
      <br />
      <BorderLinearProgress variant="determinate" value={value} />
    </Stack>
  );
}

export default ProgressBar;
