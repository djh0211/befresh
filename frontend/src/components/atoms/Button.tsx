import styled from 'styled-components';

const MyButton = styled.button`
  background-color: #097539;
  color: white;
  font-size: 20px;
  padding: 10px 20px;
  border: none;
  border-radius: 1rem;
  cursor: pointer;
  width: 100%;
  min-height: 80px;
  font-family: 'TheJamsil5Bold', sans-serif;

  &:hover {
    background-color: #0f9240;
  }

  @media (min-width: 768px) {
    font-size: 30px;
  }
`;

export default MyButton;
