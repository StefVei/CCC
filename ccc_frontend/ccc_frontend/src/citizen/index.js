import React , {useState} from 'react';
import { Typography } from '@mui/material';
import useStyles from './styles';
// import { useNavigate } from 'react-router-dom';
// import { cccClient } from '../network';

function Citizen() {
  //   const navigate = useNavigate();
  const styles = useStyles();
  const [firstName, getFirstName] = useState('');
  const [lastName, getLastName] = useState('');
  const [amka, getAmka] = useState('');
  const [vat, getVat] = useState('');
  const [birthDate, getBirthDate] = useState('');
  const [gender, getGender] = useState('');
  const [phone, getPhone] = useState('');
  const [address, getAddress] = useState('');
  const [amountDue, getAmountDue] = useState('');
  const [creditLimit, getCreditLimit] = useState('');




  const getCitizens = async () => {
    await cccClient
      .post('getCitizens', `userid=${userid}`)
      .then(function (response) {
        console.log(response);
        setProducts(response.data);
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  return (
    <div className={styles.container}>
      <div className={styles.textContainer}>
        <Typography alignSelf={'center'} variant="h4">
          Citizen
        </Typography>
        <Box pt={3}>
          <TextField
            label="FirstName:"
            variant="readOnly"
            value={firstName}
            onChange={(e) => getFirstName(e.target.value)}
            required
          />{' '}
        </Box>
        <Box pt={3}>
          <TextField
            label="LastName:"
            variant="readOnly"
            value={price}
            onChange={(e) => getLastName(e.target.value)}
            required
          />{' '}
        </Box>
        <Box pt={3}>
          <TextField
            label="Amka :"
            variant="readOnly"
            type="number"
            value={quantity}
            onChange={(e) => getAmka(e.target.value)}
            required
          />{' '}
        </Box>
        <Box pt={3}>
          <TextField
            label="Amka :"
            variant="readOnly"
            type="number"
            value={quantity}
            onChange={(e) => getAmka(e.target.value)}
            required
          />{' '}
        </Box>
        <Box pt={3}>
          <TextField
            label="Amka :"
            variant="readOnly"
            type="number"
            value={quantity}
            onChange={(e) => getAmka(e.target.value)}
            required
          />{' '}
        </Box>
      </div>
    </div>
  );
}

export default Citizen;
