import styled from "styled-components";
import SignUpTemp from "../components/templates/signupTemp";
import { signUp, logIn } from "../api/member/memberApi";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { useState } from "react";
import { saveTokens, getFcmToken } from "../utils/tokenUtils";
import { sendFcmToken } from "../api/alarm/alarmApi";

const SignUpPageContainer = styled.div`
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

function SignupPage() {
  const navigator = useNavigate()
  const fcmToken = getFcmToken()
  // const { refId } = useParams<{ refId: string }>(); // refId 파라미터 받아오기

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
          if (loginResponse.headers) {
            console.log("헤더있음")
            const accessToken = loginResponse.headers.get('Authorization');
            const refreshToken = loginResponse.headers.get('Authorization-refresh');
            if (accessToken && refreshToken) {
              // 토큰이 있는 경우에만 저장
              saveTokens(accessToken, refreshToken);
              // fcm 토큰도 같이 보내주기
              if (fcmToken) {
                sendFcmToken(fcmToken, accessToken)
              }
            }
            
            navigator('/main')
          }
        }
      }
    } catch (error) {
      console.error("안돌아감:", error);
    }
  };

  // 냉장고 아이디(refId)
  const [refId, setRefId] = useState<string|null>(null)
  useEffect(()=> {
    let query = window.location.search
    let param = new URLSearchParams(query);
    let tempRefid = param.get('refId');
    setRefId(tempRefid)
  }, [])

  const getRefId = (ref:string) => {
    setRefId(ref)
  }


  return (
    <SignUpPageContainer>
      <SignUpTemp onSignUp={handleSignUp} refId={refId} getRefId={getRefId}/>
    </SignUpPageContainer>
  );
}

export default SignupPage;
