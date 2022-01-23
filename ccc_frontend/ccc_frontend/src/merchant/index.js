import React from 'react';
import { Typography, Button, Box } from '@mui/material';
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
          Merchant
        </Typography>
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <Button
          variant="contained"
          color="primary"
          onClick={() => navigate('/ManageProducts', { state: { userid: userid } })}>
          Manage Products
        </Button>
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <Button
          variant="contained"
          color="primary"
          onClick={() => navigate('/MerchantTransactionHistory', { state: { userid: userid } })}>
          Transaction History
        </Button>
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <Button
          variant="contained"
          color="primary"
          onClick={() => navigate('/MerchantInfo', { state: { userid: userid } })}>
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
