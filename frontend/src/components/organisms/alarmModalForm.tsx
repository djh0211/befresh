import { IconButton, Modal } from "@mui/material";
import SettingsIcon from '@mui/icons-material/Settings';
import AlarmSettingBlock from "../molecules/alarmSettingBlock";
import { useState } from "react";
import styled from "styled-components";

export default function AlarmModalForm() {
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const ModalBox = styled.div`
    width: 50vw;
    height: 20vh;
    background-color: #ffffff;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    padding: 30px;
`
  
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
        <ModalBox>
          <AlarmSettingBlock/>
        </ModalBox>
      </Modal>
    </>
  )
}