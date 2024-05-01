// SignupPage.tsx

import styled from "styled-components";
import SignUpTemp from "../components/templates/signupTemp";
import { signUp } from "../api/member/memberApi"; // signUp API import

const SignUpPageContainer = styled.div`
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

function SignupPage() {
  // 회원가입 함수
  const handleSignUp = async (formData: SignUpFormData) => {
    try {
      // 회원가입 요청 보내기
      const response = await signUp(formData);
      // 회원가입 성공 시 처리
      console.log("회원가입 성공:", response);
    } catch (error) {
      // 회원가입 실패 시 처리
      console.error("회원가입 실패:", error);
    }
  };

  return (
    <SignUpPageContainer>
      <SignUpTemp onSignUp={handleSignUp} />
    </SignUpPageContainer>
  );
}

export default SignupPage;
