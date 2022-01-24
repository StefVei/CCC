import React, { useState, useEffect } from 'react';
import { Typography, Box, TextField, Button } from '@mui/material';
import useStyles from './styles';
import { useNavigate, useLocation } from 'react-router-dom';
import { cccClient } from '../network';

function EmployeeInfo() {
  const navigate = useNavigate();
  const { state } = useLocation();
  const { employeeId } = state;
  const styles = useStyles();
  const [firstName, setFirstName] = useState('');
  const [birthDate, setBirthDate] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [gender, setGender] = useState('');
  const [phone, setPhone] = useState('');
  const [address, setAddress] = useState('');

  useEffect(() => {
    getCompany();
  }, []);

  const getCompany = async () => {
    await cccClient
      .post('getEmployee', `employeeId=${employeeId}`)
      .then(function (response) {
        setFirstName(response.data.first_name);
        setLastName(response.data.last_name);
        setEmail(response.data.email);
        setBirthDate(response.data.birth_date);
        if (response.data.gender === 'F') {
          setGender('Female');
        } else if (response.data.gender === 'M') {
          setGender('Male');
        } else {
          setGender('Unknown');
        }
        setPhone(response.data.phone);
        setAddress(response.data.address);
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  const deleteAccount = async () => {
    await cccClient
      .post('CloseEmployeeAccount', `employeeId=${employeeId}`)
      .then(() => {
        navigate('/');
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  return (
    <div className={styles.container}>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <Typography alignSelf={'center'} variant="h4">
          Employee Info
        </Typography>
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="First Name :"
          variant="outlined"
          disabled
          type="string"
          value={firstName}
          onChange={(e) => setFirstName(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="Last Name :"
          variant="outlined"
          disabled
          type="string"
          value={lastName}
          onChange={(e) => setLastName(e.target.value)}
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
          label="BirthDate :"
          variant="outlined"
          disabled
          value={birthDate}
          onChange={(e) => setBirthDate(e.target.value)}
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
        <Button variant="outlined" color="primary" onClick={() => navigate(-1)}>
          Back
        </Button>
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <Button variant="contained" color="error" onClick={() => deleteAccount()}>
          Delete Account
        </Button>
      </Box>
    </div>
  );
}

export default EmployeeInfo;
