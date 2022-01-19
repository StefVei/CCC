import React, { useEffect } from 'react';
import { Typography, Box, Button } from '@mui/material';
import useStyles from './styles';
import { useNavigate, useLocation } from 'react-router-dom';
// import { cccClient } from '../network';

function Citizen() {
  const navigate = useNavigate();
  const { state } = useLocation();
  const { userid } = state;
  const styles = useStyles();

  useEffect(() => {
    console.log('🚀 ~ file: index.js ~ line 11 ~ Citizen ~ userid', userid);
  }, []);

  return (
    <div className={styles.container}>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <Typography alignSelf={'center'} variant="h4">
          Citizen
        </Typography>
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <Button
          variant="contained"
          color="primary"
          onClick={() => navigate('/Products', { state: { userid: userid } })}>
          Available Products
        </Button>
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <Button
          variant="contained"
          color="primary"
          onClick={() => navigate('/CitizenInfo', { state: { userid: userid } })}>
          Personal Info
        </Button>
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <Button variant="contained" color="primary" onClick={() => navigate('/')}>
          Logout
        </Button>
      </Box>
    </div>
  );
}

export default Citizen;
