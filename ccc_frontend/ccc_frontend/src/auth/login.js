import React, { useState } from 'react';
import { TextField, Button, Typography } from '@mui/material';
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
        <Typography alignSelf={'center'} variant="h4">
          Login
        </Typography>
        <TextField
          label="Username:"
          variant="filled"
          value={Username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <TextField
          label="Password:"
          type="password"
          variant="filled"
          value={Password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <div className={styles.buttonContainer}>
          <Button type="submit" variant="contained" color="primary" onClick={() => handleSubmit()}>
            Login
          </Button>
          <Button variant="outlined" color="primary" onClick={() => navigate('/')}>
            Cancel
          </Button>
        </div>
      </div>
    </div>
  );
}

export default Login;
