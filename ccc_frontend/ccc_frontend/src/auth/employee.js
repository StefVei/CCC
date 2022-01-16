import React, { useState } from 'react';
import {
  TextField,
  Button,
  Typography,
  FormControl,
  FormLabel,
  RadioGroup,
  FormControlLabel,
  Radio,
  Box
} from '@mui/material';
import AdapterDay from '@mui/lab/AdapterDayjs';
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import DesktopDatePicker from '@mui/lab/DesktopDatePicker';
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
  const [Firstname, setFirstname] = useState('');
  const [Lastname, setLastname] = useState('');
  const [CompanyName, setCompanyName] = useState('');
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
        'OpenEmployeeAccount',
        `username=${Username}&password=${Password}&email=${Email}&address=${Address}&name=${CompanyName}&phone=${Phone}&gender=${Gender}&lastname=${Lastname}&firstname=${Firstname}&birthDate=${BirthDate.toISOString().slice(
          0,
          10
        )}`
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
            type="password"
            variant="filled"
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
            label="Company Name:"
            variant="filled"
            value={CompanyName}
            onChange={(e) => setCompanyName(e.target.value)}
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
