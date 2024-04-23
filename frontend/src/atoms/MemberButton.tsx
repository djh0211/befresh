// Button.js
import styled from 'styled-components';

const MemberButton = styled.button`
  background-color: #097539;
  color: white;
  font-size: 20px;
  padding: 10px 20px;
  margin: 30px
  border: none;
  border-radius: 1rem;
  cursor: pointer;

  &:hover {
    background-color: #0f9240;
  }

  @media (min-width: 768px) {
    font-size: 30px;
  }
`;

export default MemberButton;
