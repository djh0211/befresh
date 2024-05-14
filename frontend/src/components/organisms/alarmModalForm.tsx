import { Box, IconButton, Modal } from "@mui/material";
import SettingsIcon from '@mui/icons-material/Settings';
import AlarmSettingBlock from "../molecules/alarmSettingBlock";
import { useState } from "react";

export default function AlarmModalForm() {
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const modalBox = {
    'width': '50vw',
    'height': '20vh',
    'backgroundColor': '#ffffff',
    'position': 'absolute',
    'top': '50%',
    'left': '50%',
    'transform': 'translate(-50%, -50%)',
    'padding': '30px'
  }

  return(
    <>
      <IconButton
        onClick={handleOpen}
        sx={{position: "absolute", right:'5%', top:'2%'}}
      >
        <SettingsIcon sx={{fontSize: '60px'}}/>
      </IconButton>
      <Modal
        open={open}
        onClose={handleClose}
      >
        <Box sx={modalBox}>
          <AlarmSettingBlock close={handleClose}/>
        </Box>
      </Modal>
    </>
  )
}