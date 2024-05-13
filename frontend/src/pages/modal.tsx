import * as React from "react";
import { useState } from "react"; // import를 수정
import Button from "@mui/joy/Button";
import Modal from "@mui/joy/Modal";
import ModalClose from "@mui/joy/ModalClose";
import Typography from "@mui/joy/Typography";
import Sheet from "@mui/joy/Sheet";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import { FoodData, FoodTypes } from "../types/foodTypes";
import { modalFoodDetail, updateFoodDetail } from "../api/food/foodModalApi";
import { formatDate } from "../utils/dateUtils";
import sampleimg from "../../src/assets/sampleimg.png";
import dayjs, { Dayjs } from "dayjs";
import { DemoContainer } from "@mui/x-date-pickers/internals/demo";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";

const FoodModalDetail = styled.div`
  display: flex;
  justify-content: space-between;
`;
const DetailList = styled.p`
  width: 150px;
  text-align: center;
`;

const DetailInfo = styled.p`
  width: 220px;
  text-align: right;
`;

const EditIcon = styled.span`
  cursor: pointer;
`;

interface ModalProps {
  foodData: FoodData;
  cardApiData: FoodTypes[];
  setData: React.Dispatch<React.SetStateAction<FoodTypes[]>>;
}

const BasicModal: React.FC<ModalProps> = ({
  foodData,
  cardApiData,
  setData,
}) => {
  const [open, setOpen] = useState<boolean>(false); // useState로 변경
  const [foodDetail, setFoodDetail] = useState<FoodData | null>(null);
  const [editedName, setEditedName] = useState<string>(foodData.name);
  const [editedExpirationDate, setEditedExpirationDate] = useState<Dayjs | null>( // Dayjs | null 타입으로 변경
    dayjs(foodData.expirationDate)
  );
  const [openDatePicker, setOpenDatePicker] = useState<boolean>(false);
  const navigate = useNavigate();
  const newimage =
    foodDetail?.image != null
      ? foodDetail?.image.replace(/\\/g, "")
      : sampleimg;

  // 모달 열기
  const handleOpen = async () => {
    setOpen(true);
    try {
      // 상세 정보를 가져옴
      const detail = await modalFoodDetail(foodData.id);
      console.log(detail, "상세정보 불러온후 디테일제대로 나오나?");
      setFoodDetail(detail.result);
    } catch (error) {
      console.error("상세 정보를 가져오는 중에 오류:", error);
    }
  };

  // 모달 닫기
  const handleClose = () => {
    setOpen(false);
  };

  // 음식 정보 업데이트
  const handleUpdateFoodDetail = async () => {
    try {
      console.log(foodDetail, "유통유통");
      // 수정된 이름과 유통기한을 포함하여 API로 요청을 보냄
      await updateFoodDetail({
        foodId: foodData.id,
        name: editedName,
        expirationDate: editedExpirationDate?.toISOString() ?? "", // Dayjs 객체를 문자열로 변환
      });

      // 모달을 닫고 상태를 초기화함
      setOpen(false);
      // 수정된 값으로 상태를 갱신합니다.
      const updatedData = cardApiData.map((food) => {
        if (food.id === foodData.id) {
          return {
            ...food,
            name: editedName,
            expirationDate: editedExpirationDate?.toISOString() ?? "", // Dayjs 객체를 문자열로 변환
          };
        }
        return food;
      });
      setData(updatedData);
    } catch (error) {
      console.error(
        "음식 정보를 업데이트하는 중에 오류가 발생했습니다:",
        error
      );
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
        sx={{ display: "flex", justifyContent: "center", alignItems: "center" }}
      >
        <Sheet
          variant="outlined"
          color="success"
          sx={{
            padding: "40vw",
            minWidth: "50vw",
            minHeight: "50vh",
            borderRadius: "md",
            p: 5,
            boxShadow: "lg",
            display: "flex",
            flexDirection: "column",
            justifyContent: "space-around",
          }}
        >
          <ModalClose variant="plain" sx={{ m: 1 }} />
          {foodDetail?.image && (
            <img
              src={newimage}
              alt="음식 이미지"
              style={{
                width: "200px",
                height: "auto",
                maxHeight: "200px",
                maxWidth: "200px",
                objectFit: "cover",
              }}
            />
          )}
          <Typography
            component="h2"
            id="modal-title"
            level="h4"
            textColor="inherit"
            fontWeight="lg"
            mb={2}
            sx={{ fontSize: "1.5rem" }}
          >
            <input
              type="text"
              value={editedName}
              onChange={(e) => setEditedName(e.target.value)}
              style={{
                fontSize: "2rem",
                border: "none",
                borderBottom: "1px solid black",
                width: "40%",
                padding: "10px",
              }}
            />
          </Typography>
          <Typography
            id="modal-desc"
            textColor="text.tertiary"
            sx={{ fontSize: "1.2rem" }}
          >
            <FoodModalDetail>
              <DetailList>들어온시간</DetailList>
              <p>:</p>
              <DetailInfo>{formatDate(foodDetail?.regDttm ?? "")}</DetailInfo>
            </FoodModalDetail>
            <FoodModalDetail>
              <DetailList>경과시간</DetailList>
              <p>:</p>
              <DetailInfo>{foodDetail?.elapsedTime} 일</DetailInfo>
            </FoodModalDetail>
            <FoodModalDetail>
              <DetailList>유통기한</DetailList>
              <p>:</p>
              <DetailInfo>
                <LocalizationProvider dateAdapter={AdapterDayjs}>
                  <DemoContainer components={["DatePicker"]}>
                    <DatePicker
                      value={editedExpirationDate ?? null} // 수정
                      onChange={(newValue: any) => setEditedExpirationDate(newValue ? dayjs(newValue) : null)} // Date 객체를 Dayjs 객체로 변환
                    />
                  </DemoContainer>
                </LocalizationProvider>
              </DetailInfo>
              {/* <p>
                <EditIcon onClick={() => setOpenDatePicker(!openDatePicker)}>✏️</EditIcon>
              </p> */}
            </FoodModalDetail>
            <FoodModalDetail>
              <DetailList>상태</DetailList>
              <p>:</p>
              <DetailInfo>{foodDetail?.refresh}</DetailInfo>
            </FoodModalDetail>
            {foodData.ftype === "용기" && (
              <React.Fragment>
                <FoodModalDetail>
                  <DetailList>온도</DetailList>
                  <p>:</p>
                  <DetailInfo>{foodDetail?.temperature} °C</DetailInfo>
                </FoodModalDetail>
                <FoodModalDetail>
                  <DetailList>습도</DetailList>
                  <p>:</p>
                  <DetailInfo>{foodDetail?.humidity} %</DetailInfo>
                </FoodModalDetail> 
                <div
                  style={{
                    display: "flex",
                    marginTop: "10px",
                    justifyContent: "flex-end",
                    marginRight: "10px",
                  }}
                >
                  <Button
                    onClick={() => navigate("/info")}
                    sx={{ width: "12vw", height: "3vh", fontSize: "1rem" }}
                  >
                    세부 기록
                  </Button>
                </div>
              </React.Fragment>
            )}
            <div
              style={{
                display: "flex",
                marginTop: "10px",
                justifyContent: "flex-end",
                marginRight: "10px",
              }}
            >
              <Button
                onClick={handleUpdateFoodDetail}
                sx={{ width: "12vw", height: "3vh", fontSize: "1rem" }}
              >
                저장
              </Button>
            </div>
          </Typography>
        </Sheet>
      </Modal>
    </React.Fragment>
  );
};

export default BasicModal;
