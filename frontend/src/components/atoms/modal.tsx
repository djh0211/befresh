import * as React from 'react';
import Button from '@mui/joy/Button';
import Modal from '@mui/joy/Modal';
import ModalClose from '@mui/joy/ModalClose';
import Typography from '@mui/joy/Typography';
import Sheet from '@mui/joy/Sheet';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';

const FoodModalDetail = styled.div`
  display: flex;
  justify-content: space-between;
`;
const DetailList = styled.p`
  width: 120px; /* 원하는 길이로 조절하세요 */
  text-align: center;
`;

export default function BasicModal() {
  const [open, setOpen] = React.useState<boolean>(false);
  const navigate = useNavigate()
  return (
    <React.Fragment>
      <Button variant="outlined" color="neutral" onClick={() => setOpen(true)}>
        상세보기
      </Button>
      <Modal
        aria-labelledby="modal-title"
        aria-describedby="modal-desc"
        open={open}
        onClose={() => setOpen(false)}
        sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}
      >
        <Sheet
          variant="outlined"
          color="success"
          sx={{
            maxWidth: 500,
            borderRadius: 'md',
            p: 3,
            boxShadow: 'lg',
          }}
        >
          <ModalClose variant="plain" sx={{ m: 1 }} />
          <img src="../../../public/sampleimg.png" alt="img" />
          <Typography
            component="h2"
            id="modal-title"
            level="h4"
            textColor="inherit"
            fontWeight="lg"
            mb={1}
          >
            음식이름
          </Typography>
          <Typography id="modal-desc" textColor="text.tertiary">
            <FoodModalDetail><DetailList>들어온시간  </DetailList><p>:</p><p>24.00.00</p></FoodModalDetail>
            <FoodModalDetail><DetailList>경과시간  </DetailList><p>:</p><p>24.00.00</p></FoodModalDetail>
            <FoodModalDetail><DetailList>유통기한  </DetailList><p>:</p><p>24.00.00</p></FoodModalDetail>
            <FoodModalDetail><DetailList>상태  </DetailList><p>:</p><p>24.00.00</p></FoodModalDetail>
            
            <Button onClick={() => { navigate('/info') }}>세부 기록</Button>
            
          </Typography>
        </Sheet>
      </Modal>
    </React.Fragment>
  );
}