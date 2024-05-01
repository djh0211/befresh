// 리덕스 스토어에서 냉장고 ID를 가져오는 컨테이너 컴포넌트
import { connect } from 'react-redux';
import SignUpForm from '../components/organisms/signupForm';

const mapStateToProps = (state : any) => {
  return {
    refrigeratorId: state.refrigeratorId, // 리덕스 스토어로부터 냉장고 ID 가져오기
  };
};

const SignUpFormContainer = connect(mapStateToProps)(SignUpForm);

export default SignUpFormContainer;
