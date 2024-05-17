import styled from 'styled-components';
import { Link } from 'react-router-dom';
import AlarmIcon from "../atoms/alarmIcon";
import InfoIcon from "../atoms/infoIcon";
import HomeIcon from "../atoms/homeIcon";
import Stack from '@mui/material/Stack';

// 세로로 가운데 정렬된 요소를 감싸는 컨테이너 스타일드 컴포넌트
const CenteredStack = styled(Stack)`
  display: flex;
  align-items: center;
  justify-content: space-around;
  border-top: 1px solid #ccc;
  padding: 10px;
  height: 12vh;
  @media (orientation: portrait) {
    height: 8vh;
  }
`;


export default function NavBlock() {
  return (
    <CenteredStack direction="row" spacing={3}>
      <Link to="/info"><InfoIcon /></Link>
      <Link to="/main"><HomeIcon /></Link>
      <Link to="/alarm"><AlarmIcon /></Link>
    </CenteredStack>
  );
}
