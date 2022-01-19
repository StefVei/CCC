import React, { useState, useEffect } from 'react';
import { Typography, Box, TextField, Button } from '@mui/material';
import useStyles from './styles';
import { useNavigate, useLocation } from 'react-router-dom';
import { cccClient } from '../network';

function CitizenInfo() {
  const navigate = useNavigate();
  const { state } = useLocation();
  const { userid } = state;
  const styles = useStyles();
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [amka, setAmka] = useState('');
  const [vat, setVat] = useState('');
  const [birthDate, setBirthDate] = useState('');
  const [gender, setGender] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [address, setAddress] = useState('');
  const [amountDue, setAmountDue] = useState('');
  const [creditLimit, setCreditLimit] = useState('');
  const [creditBalance, setCreditBalance] = useState('');
  const [dueDate, setDueDate] = useState('');

  useEffect(() => {
    getCitizen();
  }, []);

  const getCitizen = async () => {
    await cccClient
      .post('GetCitizen', `userid=${userid}`)
      .then(function (response) {
        setFirstName(response.data.first_name);
        setLastName(response.data.last_name);
        setAmka(response.data.amka);
        setVat(response.data.vat);
        setBirthDate(response.data.birth_date);
        if (response.data.gender === 'F') {
          setGender('Female');
        } else if (response.data.gender === 'M') {
          setGender('Male');
        } else {
          setGender('Unknown');
        }
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
          Citizen Info
        </Typography>
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="FirstName:"
          variant="outlined"
          disabled
          value={firstName}
          onChange={(e) => setFirstName(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="LastName:"
          variant="outlined"
          disabled
          value={lastName}
          onChange={(e) => setLastName(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="Amka :"
          variant="outlined"
          disabled
          value={amka}
          onChange={(e) => setAmka(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="VAT :"
          variant="outlined"
          disabled
          value={vat}
          onChange={(e) => setVat(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="BirthDate :"
          variant="outlined"
          disabled
          value={birthDate}
          onChange={(e) => setBirthDate(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="Gender :"
          variant="outlined"
          disabled
          type="string"
          value={gender}
          onChange={(e) => setGender(e.target.value)}
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
          onClick={() => navigate('/Citizen', { state: { userid: userid } })}>
          Back
        </Button>
      </Box>
    </div>
  );
}

export default CitizenInfo;