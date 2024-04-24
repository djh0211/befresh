import IdInputTextFields from '../atoms/IdInput'; // inputbox.tsx 파일의 컴포넌트를 import
import PasswordInputTextFields from '../atoms/PasswordInput';

function LoginPage() {
  return (
    <div>
      {/* inputbox.tsx 파일에 정의된 컴포넌트 사용 */}
      <IdInputTextFields />
      <PasswordInputTextFields />
    </div>
  );
}

export default LoginPage;
