import React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { styled } from '@mui/material/styles'; 
import PropTypes from 'prop-types'; 

const CustomTextField = styled(TextField)({
  width:'40vw',
  height:'15vh',
  '& .MuiInputBase-input': {
    fontSize: '20px',
    width: '200px',
    height: '50px',
  },
  '& .MuiInputLabel-root': {
    '&.Mui-focused': {
      color: 'green',
    },
  },
  '& .MuiInputBase-root': {
    '&:after': {
      borderBottomColor: 'green',
    },
  },
});

interface PasswordInputTextFieldsProps {
  label: string;
  onChange?: (event: React.ChangeEvent<HTMLInputElement>) => void;
  onKeyDown?: (event: React.KeyboardEvent<HTMLInputElement>) => void;
  helperText?: string;
}

function PasswordInputTextFields({ label, onChange, onKeyDown, helperText }: Readonly<PasswordInputTextFieldsProps>) {
  return (
    <Box
      component="form"
      noValidate
      autoComplete="off"
    >
        <CustomTextField
          id="standard-password-input"
          label={label}
          type="password"
          autoComplete="current-password"
          variant="standard"
          onChange={onChange}
          onKeyDown={onKeyDown}
          error={!!helperText}
          helperText={helperText}
        />
    </Box>
  );
}

PasswordInputTextFields.propTypes = {
  label: PropTypes.string.isRequired,
  helperText: PropTypes.string,
};

export default PasswordInputTextFields;
