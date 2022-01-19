import React, { useState } from 'react';
import { TextField, Button, Typography, Box } from '@mui/material';
import useStyles from './styles';
import { useNavigate } from 'react-router-dom';
import { cccClient } from '../network';

function Login() {
  const navigate = useNavigate();
  const styles = useStyles();
  const [Username, setUsername] = useState('');
  const [Password, setPassword] = useState('');

  const handleSubmit = async () => {
    await cccClient
      .post('Login', `username=${Username}&password=${Password}`)
      .then(function (response) {
        console.log('🚀 ~ file: login.js ~ line 17 ~ response', response);
        if (response.data === 'Wrong Credentials') {
          alert(response.data);
        } else {
          if (response.data.user_type === 'C') {
            navigate('/Company', { state: { userid: response.data.userid } });
          } else if (response.data.user_type === 'I') {
            navigate('/Citizen', { state: { userid: response.data.userid } });
          } else if (response.data.user_type === 'M') {
            navigate('/Merchant', { state: { userid: response.data.userid } });
          }
        }
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  return (
    <div className={styles.container}>
      <div className={styles.textFieldContainer}>
        <Box p={1} sx={3} display="flex" justifyContent="center" alignItems="center">
          <Typography alignSelf={'center'} variant="h4">
            Login
          </Typography>
        </Box>
        <Box p={3}>
          <TextField
            label="Username:"
            variant="filled"
            value={Username}
            onChange={(e) => setUsername(e.target.value)}
          />{' '}
        </Box>
        <Box p={3}>
          <TextField
            label="Password:"
            type="password"
            variant="filled"
            value={Password}
            onChange={(e) => setPassword(e.target.value)}
          />{' '}
        </Box>
        <div className={styles.buttonContainer}>
          <Box p={3}>
            <Button
              type="submit"
              variant="contained"
              color="primary"
              onClick={() => handleSubmit()}>
              Login
            </Button>
          </Box>
          <Box p={3}>
            <Button variant="outlined" color="primary" onClick={() => navigate('/')}>
              Cancel
            </Button>
          </Box>
        </div>
      </div>
    </div>
  );
}

export default Login;
