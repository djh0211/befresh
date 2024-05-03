import React, { useState } from 'react';
import styled from "styled-components";
import SignupInputBlock from "../molecules/sighupInputBlock";
import MemberButton from "../atoms/Button";
import LogoComponent from "../atoms/LogoComponent";
import Swal from 'sweetalert2';
import { QrReader } from 'react-qr-reader';
import { Dialog, DialogContent } from '@mui/material';

const SignUpContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 90vh;
`;

interface SignUpFormData {
  id: string;
  password: string;
  refrigeratorId: string;
}


function SignUpForm({ onSignUp, refId, getRefId }: Readonly<{ onSignUp: (formData: SignUpFormData) => void, refId: string|null, getRefId: (ref:string) => void }>) {

  // 아이디, 비밀번호, 비밀번호 확인을 위한 state 선언
  const [id, setId] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  // 회원가입 버튼 클릭 시 호출되는 함수
  const handleSignUp = () => {
    if (refId !== null){
      // 비밀번호와 비밀번호 확인이 일치하는지 확인
      if (password !== confirmPassword) {
        Swal.fire({
          text: '비밀번호와 비밀번호 확인이 일치하지 않습니다.',
          icon: 'error',
        });
        return;
      }

      console.log('refId', refId)
      // 회원가입 정보를 모아서 onSignUp 콜백 함수에 전달
      const formData: SignUpFormData = {
        id: id,
        password: password,
        refrigeratorId: refId, // 가져온 냉장고 ID 사용
      };
      onSignUp(formData);
    }
  };


  // 모달용 변수들
  const [open, setOpen] = useState(false);
  const handleClose = () => {
    setOpen(false);
  };
  const openScanner = () => {
    setOpen(true)
  }

  return (
    <div>
      <SignUpContainer>
        <LogoComponent />
        {/* 아이디, 비밀번호, 비밀번호 확인을 입력받는 컴포넌트에 상태와 변경 이벤트 핸들러를 전달 */}
        <SignupInputBlock
          onIdChange={(event: React.ChangeEvent<HTMLInputElement>) => setId(event.target.value)}
          onPasswordChange={(event: React.ChangeEvent<HTMLInputElement>) => setPassword(event.target.value)}
          onConfirmPasswordChange={(event: React.ChangeEvent<HTMLInputElement>) => setConfirmPassword(event.target.value)}
        />
        {
          refId == null ? (
            <MemberButton onClick={openScanner}>냉장고 등록</MemberButton>
          ) : (
            <MemberButton onClick={handleSignUp}>회원가입</MemberButton>
          )
        }
      </SignUpContainer>
      <Dialog 
        open={open}
        onClose={handleClose}
      >
        <DialogContent>
          <div style={{width:'550px'}}>
            <p style={{textAlign:'center', fontSize:'1.3rem'}}>냉장고의 QR코드를 인식해주세요.</p>
            {open && (<QrReader
              onResult={(result:any, _err:any) => {
                if (result) {
                  let tempRefId = result?.text.split('refId=')
                  console.log(tempRefId[1])
                  getRefId(tempRefId[1])
                  setOpen(false);
                }
              }}
              constraints={{facingMode: 'environment'}}
            />)}
          </div>
        </DialogContent>
      </Dialog>
    </div>
  );
}

export default SignUpForm;
