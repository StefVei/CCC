import React from 'react';
import { Typography, Box, Button } from '@mui/material';
import useStyles from './styles';
import { useNavigate, useLocation } from 'react-router-dom';
// import { cccClient } from '../network';

function Company() {
  const navigate = useNavigate();
  const { state } = useLocation();
  const { userid } = state;
  const styles = useStyles();

  return (
    <div className={styles.container}>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <Typography alignSelf={'center'} variant="h4">
          Company
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
          onClick={() => navigate('/CompanyInfo', { state: { userid: userid } })}>
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

export default Company;
