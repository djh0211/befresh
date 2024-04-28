import AlarmIcon from "../atoms/alarmIcon";
import FailIcon from "../atoms/failIcon";
import HomeIcon from "../atoms/homeIcon";
import Stack from '@mui/material/Stack';

export default function NavBlock() {
  return (
    <Stack direction="row" spacing={3}>
      <HomeIcon />
      <FailIcon />
      <AlarmIcon />
    </Stack>
  );
}
