import Stack from '@mui/material/Stack';
import SvgIcon, { SvgIconProps } from '@mui/material/SvgIcon';

function NavHomeIcon(props: SvgIconProps) {
  return (
    <SvgIcon {...props}>
      <path d="M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z" />
    </SvgIcon>
  );
}

export default function HomeIcon() {
  return (
    <Stack direction="row" spacing={3}>
      <NavHomeIcon color="success" fontSize='large' />
    </Stack>
  );
}