import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import {
  TextField,
  Button,
  FormControl,
  FormLabel,
  RadioGroup,
  FormControlLabel,
  Radio,
  Typography,
  Box
} from '@mui/material';
import AdapterDay from '@mui/lab/AdapterDayjs';
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import DesktopDatePicker from '@mui/lab/DesktopDatePicker';
import useStyles from './styles';
import { cccClient } from '../network';

function CitizenRegister() {
  const navigate = useNavigate();
  const styles = useStyles();
  const [Username, setUsername] = useState('');
  const [Password, setPassword] = useState('');
  const [Phone, setPhone] = useState('');
  const [Email, setEmail] = useState('');
  const [Address, setAddress] = useState('');
  const [Firstname, setFirstname] = useState('');
  const [CreditBalance, setCreditBalance] = useState(1000);
  const [Lastname, setLastname] = useState('');
  const [Amka, setAmka] = useState('');
  const [Vat, setVat] = useState('');
  const [BirthDate, setBirthDate] = useState(new Date());
  const [Gender, setGender] = useState('female');

  const handleChange = (event) => {
    setGender(event.target.value);
  };

  const handleDateChange = (newValue) => {
    setBirthDate(newValue);
  };

  const handleSubmit = async () => {
    await cccClient
      .post(
        'OpenCitizenAccount',
        `username=${Username}&password=${Password}&email=${Email}&address=${Address}&firstname=${Firstname}&phone=${Phone}
        }&lastname=${Lastname}&amka=${Amka}&vat=${Vat}&birthDate=${BirthDate.toISOString().slice(
          0,
          10
        )}&gender=${Gender}`
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
          Register Citizen
        </Typography>
        <Box pt={3}>
          <TextField
            label="Username:"
            variant="filled"
            value={Username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </Box>
        <Box pt={3}>
          <TextField
            label="Password:"
            variant="filled"
            type="password"
            value={Password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </Box>
        <Box pt={3}>
          <TextField
            label="Phone:"
            variant="filled"
            value={Phone}
            onChange={(e) => setPhone(e.target.value)}
            required
            inputProps={{ maxLength: 10 }}
          />
        </Box>
        <Box pt={3}>
          <TextField
            label="Email:"
            variant="filled"
            value={Email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </Box>
        <Box pt={3}>
          <TextField
            label="Address:"
            variant="filled"
            value={Address}
            onChange={(e) => setAddress(e.target.value)}
            required
          />
        </Box>
        <Box pt={3}>
          <TextField
            label="First Name:"
            variant="filled"
            value={Firstname}
            onChange={(e) => setFirstname(e.target.value)}
            required
          />
        </Box>
        <Box pt={3}>
          <TextField
            label="Last Name:"
            variant="filled"
            value={Lastname}
            onChange={(e) => setLastname(e.target.value)}
            required
          />
        </Box>
        <Box pt={3}>
          <TextField
            label="AMKA:"
            variant="filled"
            value={Amka}
            onChange={(e) => setAmka(e.target.value)}
            required
            inputProps={{ maxLength: 11 }}
          />
        </Box>
        <Box pt={3}>
          <TextField
            label="VAT:"
            variant="filled"
            value={Vat}
            onChange={(e) => setVat(e.target.value)}
            required
            inputProps={{ maxLength: 9 }}
          />
        </Box>
        <Box pt={3}>
          <TextField
            label="Credit Balance:"
            type="number"
            variant="filled"
            disabled
            value={CreditBalance}
            onChange={(e) => setCreditBalance(e.target.value)}
            required
          />
        </Box>
        <Box pt={3}>
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
        </Box>
        <Box pt={3}>
          <LocalizationProvider dateAdapter={AdapterDay}>
            <DesktopDatePicker
              label="Birth Date"
              value={BirthDate}
              onChange={handleDateChange}
              renderInput={(params) => <TextField {...params} />}
            />
          </LocalizationProvider>
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

export default CitizenRegister;
