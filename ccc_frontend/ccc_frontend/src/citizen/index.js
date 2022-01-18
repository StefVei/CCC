import React, { useState, useEffect } from 'react';
import { Typography, Box, TextField } from '@mui/material';
import useStyles from './styles';
// import { useNavigate } from 'react-router-dom';
import { cccClient } from '../network';

function Citizen() {
  //   const navigate = useNavigate();
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
    let userid = 4;

    await cccClient
      .post('getCitizen', `userid=${userid}`)
      .then(function (response) {
        console.log(response);
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  return (
    <div className={styles.container}>
      <div className={styles.textContainer}>
        <Typography alignSelf={'center'} variant="h4">
          Citizen
        </Typography>
        <Box pt={3}>
          <TextField
            label="FirstName:"
            variant="outlined"
            defaultValue={firstName}
            onChange={(e) => setFirstName(e.target.value)}
            required
          />{' '}
        </Box>
        <Box pt={3}>
          <TextField
            label="LastName:"
            variant="outlined"
            disabled
            defaultValue={lastName}
            onChange={(e) => setLastName(e.target.value)}
            required
          />{' '}
        </Box>
        <Box pt={3}>
          <TextField
            label="Amka :"
            variant="outlined"
            defaultValue={amka}
            onChange={(e) => setAmka(e.target.value)}
            required
          />{' '}
        </Box>
        <Box pt={3}>
          <TextField
            label="VAT :"
            variant="outlined"
            defaultValue={vat}
            onChange={(e) => setVat(e.target.value)}
            required
          />{' '}
        </Box>
        <Box pt={3}>
          <TextField
            label="BirthDate :"
            variant="outlined"
            defaultValue={birthDate}
            onChange={(e) => setBirthDate(e.target.value)}
            required
          />{' '}
        </Box>
        <Box pt={3}>
          <TextField
            label="Gender :"
            variant="outlined"
            type="string"
            defaultValue={gender}
            onChange={(e) => setGender(e.target.value)}
            required
          />{' '}
        </Box>
        <Box pt={3}>
          <TextField
            label="Email :"
            variant="outlined"
            defaultValue={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />{' '}
        </Box>
        <Box pt={3}>
          <TextField
            label="Address :"
            variant="outlined"
            type="string"
            defaultValue={address}
            onChange={(e) => setAddress(e.target.value)}
            required
          />{' '}
        </Box>
        <Box pt={3}>
          <TextField
            label="Phone :"
            variant="outlined"
            defaultValue={phone}
            onChange={(e) => setPhone(e.target.value)}
            required
          />{' '}
        </Box>
        <Box pt={3}>
          <TextField
            label="Amount Due :"
            variant="outlined"
            defaultValue={amountDue}
            onChange={(e) => setAmountDue(e.target.value)}
            required
          />{' '}
        </Box>
        <Box pt={3}>
          <TextField
            label="Credit limit :"
            variant="outlined"
            defaultValue={creditLimit}
            onChange={(e) => setCreditLimit(e.target.value)}
            required
          />{' '}
        </Box>
        <Box pt={3}>
          <TextField
            label="Credit balance :"
            variant="outlined"
            defaultValue={creditBalance}
            onChange={(e) => setCreditBalance(e.target.value)}
            required
          />{' '}
        </Box>
        <Box pt={3}>
          <TextField
            label="Account due date :"
            variant="outlined"
            type="date"
            defaultValue={dueDate}
            onChange={(e) => setDueDate(e.target.value)}
            required
          />{' '}
        </Box>
      </div>
    </div>
  );
}

export default Citizen;
