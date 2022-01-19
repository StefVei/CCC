import React, { useState } from 'react';
import { TextField, Button, Typography, Box } from '@mui/material';
import useStyles from './styles';
import { useNavigate } from 'react-router-dom';
import { cccClient } from '../network';

function CompanyRegister() {
  const navigate = useNavigate();
  const styles = useStyles();
  const [Username, setUsername] = useState('');
  const [Password, setPassword] = useState('');
  const [Phone, setPhone] = useState('');
  const [Email, setEmail] = useState('');
  const [Address, setAddress] = useState('');
  const [CompanyName, setCompanyName] = useState('');
  const [CreditBalance, setCreditBalance] = useState('');

  const handleSubmit = async () => {
    await cccClient
      .post(
        'OpenCompanyAccount',
        `username=${Username}&password=${Password}&email=${Email}&address=${Address}&name=${CompanyName}&phone=${Phone}&creditBalance=${
          CreditBalance ? CreditBalance : 0
        }`
      )
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  return (
    <div className={styles.container}>
      <div className={styles.textFieldContainer}>
        <Typography alignSelf={'center'} variant="h4">
          Register Company
        </Typography>
        <Box p={3}>
          <TextField
            label="Username:"
            variant="filled"
            value={Username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </Box>
        <Box p={3}>
          <TextField
            label="Password:"
            type="password"
            variant="filled"
            value={Password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </Box>
        <Box p={3}>
          <TextField
            label="Phone:"
            variant="filled"
            value={Phone}
            onChange={(e) => setPhone(e.target.value)}
            required
            inputProps={{ maxLength: 10 }}
          />
        </Box>
        <Box p={3}>
          <TextField
            label="Email:"
            variant="filled"
            value={Email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </Box>
        <Box p={3}>
          <TextField
            label="Address:"
            variant="filled"
            value={Address}
            onChange={(e) => setAddress(e.target.value)}
            required
          />
        </Box>
        <Box p={3}>
          <TextField
            label="Company Name:"
            variant="filled"
            value={CompanyName}
            onChange={(e) => setCompanyName(e.target.value)}
            required
          />
        </Box>
        <Box p={3}>
          <TextField
            label="Credit Balance:"
            variant="filled"
            value={CreditBalance}
            onChange={(e) => setCreditBalance(e.target.value)}
            required
            type="number"
          />
        </Box>
        <div className={styles.buttonContainer}>
          <Box p={2}>
            <Button
              type="submit"
              variant="contained"
              color="primary"
              onClick={() => handleSubmit()}>
              Signup
            </Button>
          </Box>
          <Box p={2}>
            <Button variant="outlined" color="primary" onClick={() => navigate('/')}>
              Cancel
            </Button>
          </Box>
        </div>
      </div>
    </div>
  );
}

export default CompanyRegister;
