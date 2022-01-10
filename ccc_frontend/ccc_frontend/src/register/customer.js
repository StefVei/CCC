import React, { useState } from 'react';
import { TextField, Button } from '@mui/material';
import useStyles from './styles';
import { cccClient } from '../network';

function CustomerRegister() {
  const styles = useStyles();
  const [Username, setUsername] = useState('');
  const [Password, setPassword] = useState('');
  const [Phone, setPhone] = useState('');
  const [Email, setEmail] = useState('');
  const [Address, setAddress] = useState('');
  const [CompanyName, setCompanyName] = useState('');

  const handleSubmit = async () => {
    const response = await cccClient
      .post('OpenCompanyAccount', {
        Username: Username,
        Password: Password,
        Phone: Phone,
        Email: Email,
        Address: Address,
        CompanyName: CompanyName
      })
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
    console.log(response);
  };

  return (
    <div className={styles.container}>
      <div className={styles.textFieldContainer}>
        <TextField
          label="Username:"
          variant="filled"
          value={Username}
          onChange={(e) => setUsername(e.target.value)}
          required
        />
        <TextField
          label="Password:"
          variant="filled"
          value={Password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <TextField
          label="Phone:"
          variant="filled"
          value={Phone}
          onChange={(e) => setPhone(e.target.value)}
          required
        />
        <TextField
          label="Email:"
          variant="filled"
          value={Email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <TextField
          label="Address:"
          variant="filled"
          value={Address}
          onChange={(e) => setAddress(e.target.value)}
          required
        />
        <TextField
          label="Company Name:"
          variant="filled"
          value={CompanyName}
          onChange={(e) => setCompanyName(e.target.value)}
          required
        />
        <div className={styles.buttonContainer}>
          <Button type="submit" variant="contained" color="primary" onClick={() => handleSubmit()}>
            Signup
          </Button>
        </div>
      </div>
    </div>
  );
}

export default CustomerRegister;
