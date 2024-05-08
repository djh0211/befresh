import * as React from 'react';
import Button from '@mui/joy/Button';
import Modal from '@mui/joy/Modal';
import ModalClose from '@mui/joy/ModalClose';
import Typography from '@mui/joy/Typography';
import Sheet from '@mui/joy/Sheet';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import { FoodData } from '../types/foodTypes'; // FoodData 타입을 import합니다.
import { modalFoodDetail, updateFoodDetail } from '../api/food/foodModalApi'; // API 호출 함수를 import합니다.
import { formatDate } from '../utils/dateUtils'; // formatDate 함수를 import합니다.

const FoodModalDetail = styled.div`
  display: flex;
  justify-content: space-between;
`;
const DetailList = styled.p`
  width: 120px;
  text-align: center;
`;

const EditIcon = styled.span`
  cursor: pointer;
`;

interface ModalProps {
  foodData: FoodData;
}

const BasicModal: React.FC<ModalProps> = ({ foodData }) => {
  const [open, setOpen] = React.useState<boolean>(false);
  const [foodDetail, setFoodDetail] = React.useState<FoodData | null>(null); // 상세 정보를 담을 상태 변수
  const [editedName, setEditedName] = React.useState<string>(foodData.name); // 편집된 이름을 담을 상태 변수
  const [editedExpirationDate, setEditedExpirationDate] = React.useState<string>(foodData.expirationDate); // 편집된 유통기한을 담을 상태 변수
  const navigate = useNavigate();

  // 모달 열기
  const handleOpen = async () => {
    setOpen(true);
    try {
      // 상세 정보를 가져옴
      const detail = await modalFoodDetail(foodData.id);
      setFoodDetail(detail);
      console.log(detail,'상세정보 불러온후 제대로 나오나?')
    } catch (error) {
      console.error('상세 정보를 가져오는 중에 오류가 발생했습니다:', error);
    }
  };

  // 모달 닫기
  const handleClose = () => {
    setOpen(false);
  };

  // 음식 정보 업데이트
  const handleUpdateFoodDetail = async () => {
    try {
      // 수정된 이름과 유통기한을 포함하여 API로 요청을 보냄
      await updateFoodDetail({
        foodId: foodData.id,
        name: editedName,
        expirationDate: editedExpirationDate,
      });
      // 모달을 닫고 상태를 초기화함
      setOpen(false);
      // 수정된 값으로 상태를 갱신합니다.
      setEditedName(editedName);
      setEditedExpirationDate(editedExpirationDate);
    } catch (error) {
      console.error('음식 정보를 업데이트하는 중에 오류가 발생했습니다:', error);
    }
  };

  return (
    <React.Fragment>
      <Button variant="outlined" color="success" onClick={handleOpen}>
        상세보기
      </Button>
      <Modal
        aria-labelledby="modal-title"
        aria-describedby="modal-desc"
        open={open}
        onClose={handleClose}
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
            <input
              type="text"
              value={editedName}
              onChange={(e) => setEditedName(e.target.value)}
            />
          </Typography>
          <Typography id="modal-desc" textColor="text.tertiary">
            <FoodModalDetail>
              <DetailList>들어온시간</DetailList>
              <p>:</p>
              <p>{formatDate(foodData.regDttm)}</p>
            </FoodModalDetail>
            <FoodModalDetail>
              <DetailList>경과시간</DetailList>
              <p>:</p>
              <p>{foodData.elapsedTime}</p>
            </FoodModalDetail>
            <FoodModalDetail>
              <DetailList>유통기한</DetailList>
              <p>:</p>
              <p>
                {formatDate(foodData.expirationDate)}{' '}
                <EditIcon onClick={() => console.log('edit icon clicked')}>
                  ✏️
                </EditIcon>
              </p>
            </FoodModalDetail>
            <FoodModalDetail>
              <DetailList>상태</DetailList>
              <p>:</p>
              <p>{foodData.refresh}</p>
            </FoodModalDetail>
            {foodData.ftype === '용기' && (
              <React.Fragment>
                <FoodModalDetail>
                  <DetailList>온도</DetailList>
                  <p>:</p>
                  <p>{foodDetail?.temperature}</p>
                </FoodModalDetail>
                <FoodModalDetail>
                  <DetailList>습도</DetailList>
                  <p>:</p>
                  <p>{foodDetail?.humidity}</p>
                </FoodModalDetail>
                <Button onClick={() => navigate('/info')}>세부 기록</Button>
              </React.Fragment>
            )}
            <Button onClick={handleUpdateFoodDetail}>저장</Button>
          </Typography>
        </Sheet>
      </Modal>
    </React.Fragment>
  );
}

export default BasicModal;
