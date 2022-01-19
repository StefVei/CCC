import React, { useState, useEffect } from 'react';
import { Typography, Box, TextField, Button } from '@mui/material';
import useStyles from './styles';
import { useNavigate, useLocation } from 'react-router-dom';
import { cccClient } from '../network';

function CompanyInfo() {
  const navigate = useNavigate();
  const { state } = useLocation();
  const { userid } = state;
  const styles = useStyles();
  const [name, setName] = useState('');
  const [establishmentDate, setEstablishmentDate] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [address, setAddress] = useState('');
  const [amountDue, setAmountDue] = useState('');
  const [creditLimit, setCreditLimit] = useState('');
  const [creditBalance, setCreditBalance] = useState('');
  const [dueDate, setDueDate] = useState('');

  useEffect(() => {
    getCompany();
  }, []);

  const getCompany = async () => {
    await cccClient
      .post('getCompany', `userid=${userid}`)
      .then(function (response) {
        setName(response.data.name);
        setEstablishmentDate(response.data.establishment_date);
        setEmail(response.data.email);
        setPhone(response.data.phone);
        setAddress(response.data.address);
        setAmountDue(response.data.amount_due);
        setCreditBalance(response.data.credit_balance);
        setCreditLimit(response.data.credit_limit);
        setDueDate(response.data.account_due_date);
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  return (
    <div className={styles.container}>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <Typography alignSelf={'center'} variant="h4">
          Company Info
        </Typography>
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="Name :"
          variant="outlined"
          disabled
          type="string"
          value={name}
          onChange={(e) => setAddress(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="Establishment Date :"
          variant="outlined"
          disabled
          type="string"
          value={establishmentDate}
          onChange={(e) => setAddress(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="Email :"
          variant="outlined"
          disabled
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="Address :"
          variant="outlined"
          disabled
          type="string"
          value={address}
          onChange={(e) => setAddress(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="Phone :"
          variant="outlined"
          disabled
          value={phone}
          onChange={(e) => setPhone(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="Amount Due :"
          variant="outlined"
          disabled
          value={amountDue}
          onChange={(e) => setAmountDue(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="Credit limit :"
          variant="outlined"
          disabled
          value={creditLimit}
          onChange={(e) => setCreditLimit(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="Credit balance :"
          variant="outlined"
          disabled
          value={creditBalance}
          onChange={(e) => setCreditBalance(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="Account due date :"
          variant="outlined"
          disabled
          value={dueDate}
          onChange={(e) => setDueDate(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <Button
          variant="outlined"
          color="primary"
          onClick={() => navigate('/Company', { state: { userid: userid } })}>
          Back
        </Button>
      </Box>
    </div>
  );
}

export default CompanyInfo;
