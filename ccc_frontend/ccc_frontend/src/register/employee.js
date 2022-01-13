import React, { useState } from 'react';
import {
  TextField,
  Button,
  Typography,
  FormControl,
  FormLabel,
  RadioGroup,
  FormControlLabel,
  Radio
} from '@mui/material';
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
  const [Gender, setGender] = useState('female');

  const handleChange = (event) => {
    setGender(event.target.value);
  };

  const handleSubmit = async () => {
    await cccClient
      .post(
        'OpenEmployeeAccount',
        `username=${Username}&password=${Password}&email=${Email}}&address=${Address}&name=${CompanyName}&phone=${Phone}&creditBalance=${
          CreditBalance ? CreditBalance : 0
        }&gender=${Gender}`
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
          Register Employee
        </Typography>
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
        <TextField
          label="Credit Balance:"
          variant="filled"
          value={CreditBalance}
          onChange={(e) => setCreditBalance(e.target.value)}
          required
        />
        <FormControl component="fieldset">
          <FormLabel component="legend">Gender</FormLabel>
          <RadioGroup
            value={Gender}
            aria-label="gender"
            defaultValue="female"
            name="radio-buttons-group"
            onChange={handleChange}>
            <FormControlLabel value="female" control={<Radio />} label="Female" />
            <FormControlLabel value="male" control={<Radio />} label="Male" />
            <FormControlLabel value="unknown" control={<Radio />} label="Other" />
          </RadioGroup>
        </FormControl>
        <div className={styles.buttonContainer}>
          <Button type="submit" variant="contained" color="primary" onClick={() => handleSubmit()}>
            Signup
          </Button>
          <Button variant="outlined" color="primary" onClick={() => navigate('/')}>
            Cancel
          </Button>
        </div>
      </div>
    </div>
  );
}

export default CompanyRegister;