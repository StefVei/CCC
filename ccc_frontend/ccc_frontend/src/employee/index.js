import React from 'react';
import { Typography, TextField, Box, Button } from '@mui/material';
import useStyles from './styles';
import { useLocation, useNavigate } from 'react-router-dom';
// import { cccClient } from '../network';

function Employee() {
  const navigate = useNavigate();
  const { state } = useLocation();
  const { user } = state;
  const styles = useStyles();

  return (
    <div className={styles.container}>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <Typography alignSelf={'center'} variant="h4">
          Employee Credentials
        </Typography>
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <Typography alignSelf={'center'} variant="body">
          Save those crentetials in order to login.
        </Typography>
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField label="Username :" variant="outlined" disabled value={user.username} />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField label="Password :" variant="outlined" disabled value={user.password} />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <Button variant="outlined" color="primary" onClick={() => navigate('/Login')}>
          Back to Login
        </Button>
      </Box>
    </div>
  );
}

export default Employee;
