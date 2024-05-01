import axios from 'axios';
import Swal from "sweetalert2";

// 회원가입 API 
export async function signUp(formData: { id: string, password: string }): Promise<any> {
  try {
    const response = await axios.post('https://be-fresh.site/api/member/signup', formData);
    return response.data;
  } catch (err) {
    Swal.fire({
      text:'회원가입실패.',
      icon:'warning'
    })
    console.log(err);
  }
}

// 로그인 API 
export async function logIn(formData: { id: string, password: string }): Promise<any> {
  try {
    const response = await axios.post('https://be-fresh.site/api/login', formData);
    return response.data;
  } catch (err) {
    Swal.fire({
      text: '로그인 실패.',
      icon: 'warning'
    })
    console.log(err);
  }
}