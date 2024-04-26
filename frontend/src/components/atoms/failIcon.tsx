import Badge from '@mui/material/Badge';
import Stack from '@mui/material/Stack';
import ReportGmailerrorredIcon from '@mui/icons-material/ReportGmailerrorred';


export default function ColorBadge() {
  return (
    <Stack spacing={2} direction="row">
      <Badge badgeContent={4} color="success">
        <ReportGmailerrorredIcon color="action" />
      </Badge>
    </Stack>
  );
}