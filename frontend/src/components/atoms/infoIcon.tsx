import InfoIcon from '@mui/icons-material/Info';


export default function FailIcon() {
  const location :string = window.location.href
  return (
    <InfoIcon color={location.includes('info') ? "success" : "action"} sx={{fontSize:'4rem'}}/>
  );
}