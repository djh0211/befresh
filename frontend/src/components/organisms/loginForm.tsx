import React, { useState } from "react";
import styled from "styled-components";
import LoginBlock from "../molecules/loginInputBlock";
import MemberButton from "../atoms/Button";
import LogoComponent from "../atoms/LogoComponent";
import { handleEnterKeyPress } from "../../utils/buttonUtils";

const LoginPageContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 90vh;
`;

interface LoginFormData {
  id: string;
  password: string;
}

function LoginForm({
  onLogIn,
}: Readonly<{ onLogIn: (formData: LoginFormData) => void }>) {
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");

  const handleLogIn = () => {
    const formData: LoginFormData = {
      id: id,
      password: password,
    };
    onLogIn(formData);
  };

  const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
    console.log('엔터..?')
    if (event.key === "Enter") {
      handleLogIn();
    }
  };

  return (
    <div>
      <LoginPageContainer>
        <LogoComponent />
        <LoginBlock
          onIdChange={(event: React.ChangeEvent<HTMLInputElement>) =>
            setId(event.target.value)
          }
          onPasswordChange={(event: React.ChangeEvent<HTMLInputElement>) =>
            setPassword(event.target.value)
          }
          onKeyDown={handleKeyDown}
        />
        <MemberButton onClick={handleLogIn}>로그인</MemberButton>
      </LoginPageContainer>
    </div>
  );
}

export default LoginForm;
