import axios from 'axios';
import Swal from "sweetalert2";
import { saveTokens } from '../../utils/tokenUtils';

// 회원가입 API 
export async function signUp(formData: { id: string, password: string, refrigeratorId:number }): Promise<any> {
  try {
    console.log("냉장고0503",typeof(formData.refrigeratorId))
    const response = await axios.post('https://be-fresh.site/api/member/signup', formData);
    console.log(formData.refrigeratorId)
    console.log(response)
    return response.data;
  } catch (err) {
    Swal.fire({
      text:'회원가입실패.',
      icon:'warning'
    })
    console.log(err);
    return 0
  }
}

// 로그인 API 
export async function logIn(formData: { id: string, password: string }): Promise<any> {
  try {
    const response = await axios.post('https://be-fresh.site/api/login', formData);
    if (response.headers) {
      console.log("헤더있음")
    }
    return response;
  } catch (err) {
    Swal.fire({
      text: '로그인 실패.',
      icon: 'warning'
    })
    console.log(err);
    return 0
  }
}