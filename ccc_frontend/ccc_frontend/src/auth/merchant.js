import React, { useState } from 'react';
import {
  TextField,
  Button,
  FormControl,
  FormLabel,
  RadioGroup,
  FormControlLabel,
  Radio,
  Typography
} from '@mui/material';
import { useNavigate } from 'react-router-dom';
import useStyles from './styles';
import { cccClient } from '../network';

function MerchantRegister() {
  const navigate = useNavigate();
  const styles = useStyles();
  const [Username, setUsername] = useState('');
  const [Password, setPassword] = useState('');
  const [Email, setEmail] = useState('');
  const [Address, setAddress] = useState('');
  const [Firstname, setFirstname] = useState('');
  const [Lastname, setLastname] = useState('');
  const [BirthDate, setBirthDate] = useState('');
  const [Gender, setGender] = useState('female');

  const handleChange = (event) => {
    setGender(event.target.value);
  };

  const handleSubmit = async () => {
    await cccClient
      .post(
        'OpenMerchantAccount',
        `username=${Username}&password=${Password}&email=${Email}&address=${Address}&firstname=${Firstname}&lastname=${Lastname}&birthDate=${BirthDate}&gender=${Gender}`
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
          Register Merchant
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
          label="First Name:"
          variant="filled"
          value={Firstname}
          onChange={(e) => setFirstname(e.target.value)}
          required
        />
        <TextField
          label="Last Name:"
          variant="filled"
          value={Lastname}
          onChange={(e) => setLastname(e.target.value)}
          required
        />
        <TextField
          label="Birth Date:"
          variant="filled"
          value={BirthDate}
          onChange={(e) => setBirthDate(e.target.value)}
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

export default MerchantRegister;
