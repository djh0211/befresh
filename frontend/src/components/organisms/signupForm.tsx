import React, { useState } from 'react';
import styled from "styled-components";
import SignupInputBlock from "../molecules/sighupInputBlock";
import MemberButton from "../atoms/Button";
import LogoComponent from "../atoms/LogoComponent";
import Swal from 'sweetalert2';
// import { useSelector } from 'react-redux';

const SignUpContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 90vh;
`;
interface SignUpFormData {
  id: string;
  password: string;
  refrigeratorId: number;
}


function SignUpForm({ onSignUp }: Readonly<{ onSignUp: (formData: SignUpFormData) => void }>) {
  // 리덕스 스토어에서 냉장고 ID 가져오기
  // const refrigeratorId = useSelector((state: RootState) => state.refrigerator.id);

  // 아이디, 비밀번호, 비밀번호 확인을 위한 state 선언
  const [id, setId] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  // 회원가입 버튼 클릭 시 호출되는 함수
  const handleSignUp = () => {
    // 비밀번호와 비밀번호 확인이 일치하는지 확인
    if (password !== confirmPassword) {
      Swal.fire({
        text: '비밀번호와 비밀번호 확인이 일치하지 않습니다.',
        icon: 'error',
      });
      return;
    }
    // 회원가입 정보를 모아서 onSignUp 콜백 함수에 전달
    const formData: SignUpFormData = {
      id: id,
      password: password,
      // refrigeratorId: refrigeratorId, // 가져온 냉장고 ID 사용
      refrigeratorId: 101, // 가져온 냉장고 ID 사용
    };
    onSignUp(formData);
  };
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
        <MemberButton onClick={handleSignUp}>회원가입</MemberButton>
      </SignUpContainer>
    </div>
  );
}

export default SignUpForm;
