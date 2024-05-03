import styled from "styled-components";
import SignUpTemp from "../components/templates/signupTemp";
import { signUp, logIn } from "../api/member/memberApi";
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";

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
  const navigator = useNavigate()
  const { refId } = useParams<{ refId: string }>(); // refId 파라미터 받아오기

  const handleSignUp = async (formData: SignUpFormData) => {
    console.log(formData)
    // 여기서 refId와 formData를 합쳐서 API 호출 등의 로직을 수행
    try {
      // 회원가입 할 때 refId는 url 에서 따와야함
      const response = await signUp({ ...formData, refrigeratorId: Number(refId) })
      if (response !== 0) {
        console.log("dd",response)
        const loginResponse = await logIn({ id: formData.id, password: formData.password })
        if (loginResponse !== 0) {
          navigator('/login')
        }
      }
      // 로그인 성공 시 메인 페이지로 리다이렉트
      console.log("돌아는감:", response);
            if (response !== 0) {
        navigator('/main')
      }
    } catch (error) {
      console.error("안돌아감:", error);
    }
  };

  return (
    <SignUpPageContainer>
      <SignUpTemp onSignUp={handleSignUp} />
    </SignUpPageContainer>
  );
}

export default SignupPage;
