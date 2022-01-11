import React, { useState } from 'react';
import {
  TextField,
  Button,
  FormControl,
  FormLabel,
  RadioGroup,
  FormControlLabel,
  Radio
} from '@mui/material';
import useStyles from './styles';
import { cccClient } from '../network';

function CitizenRegister() {
  const styles = useStyles();
  const [Username, setUsername] = useState('');
  const [Password, setPassword] = useState('');
  const [Phone, setPhone] = useState('');
  const [Email, setEmail] = useState('');
  const [Address, setAddress] = useState('');
  const [Firstname, setFirstname] = useState('');
  const [CreditBalance, setCreditBalance] = useState('');
  const [Lastname, setLastname] = useState('');
  const [Amka, setAmka] = useState('');
  const [Vat, setVat] = useState('');
  const [BirthDate, setBirthDate] = useState('');
  const [Gender, setGender] = useState('female');

  const handleChange = (event) => {
    setGender(event.target.value);
  };

  const handleSubmit = async () => {
    await cccClient
      .post(
        'OpenCitizenAccount',
        `username=${Username}&password=${Password}&email=${Email}&address=${Address}&firstname=${Firstname}&phone=${Phone}&creditBalance=${
          CreditBalance ? CreditBalance : 0
        }&lastname=${Lastname}&amka=${Amka}&vat=${Vat}&birthDate=${BirthDate}&gender=${Gender}`
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
          label="AMKA:"
          variant="filled"
          value={Amka}
          onChange={(e) => setAmka(e.target.value)}
          required
        />
        <TextField
          label="VAT:"
          variant="filled"
          value={Vat}
          onChange={(e) => setVat(e.target.value)}
          required
        />
        <TextField
          label="Birth Date:"
          variant="filled"
          value={BirthDate}
          onChange={(e) => setBirthDate(e.target.value)}
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
        </div>
      </div>
    </div>
  );
}

export default CitizenRegister;
