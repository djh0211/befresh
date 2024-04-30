import styled from 'styled-components';
import { Link } from 'react-router-dom';
import AlarmIcon from "../atoms/alarmIcon";
import FailIcon from "../atoms/failIcon";
import HomeIcon from "../atoms/homeIcon";
import Stack from '@mui/material/Stack';

// 세로로 가운데 정렬된 요소를 감싸는 컨테이너 스타일드 컴포넌트
const CenteredStack = styled(Stack)`
  display: flex;
  align-items: center;
  justify-content: space-around;
  border: 1px solid #ccc;
  padding: 10px;
`;

export default function NavBlock() {
  return (
    <CenteredStack direction="row" spacing={3}>
      <Link to="/unregister"><FailIcon /></Link>
      <Link to="/main"><HomeIcon /></Link>
      <Link to="/alarm"><AlarmIcon /></Link>
    </CenteredStack>
  );
}
