import React, { useState, useEffect } from 'react';
import { Typography, Box, TextField, Button, Modal } from '@mui/material';
import useStyles from './styles';
import { useNavigate, useLocation } from 'react-router-dom';
import { cccClient } from '../network';

function MerchantInfo() {
  const navigate = useNavigate();
  const { state } = useLocation();
  const { userid } = state;
  const styles = useStyles();
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [supply, setSupply] = useState('');
  const [gain, setGain] = useState('');
  const [birthDate, setBirthDate] = useState('');
  const [gender, setGender] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [address, setAddress] = useState('');
  const [amountDue, setAmountDue] = useState('');
  const [purchasesTotal, setPurchasesTotal] = useState('');
  const [open, setOpen] = useState(false);
  const [amount, setAmount] = useState(0);

  useEffect(() => {
    getMerchant();
  }, []);

  const handleOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
    setAmount(0);
  };

  const handleAmount = (e) => {
    if (amountDue - e >= 0 && e >= 0) {
      setAmount(e);
    }
  };

  const deleteAccount = async () => {
    await cccClient
      .post('CloseMerchantAccount', `userId=${userid}`)
      .then(() => {
        navigate('/');
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  const makePayment = async () => {
    await cccClient
      .post('makePendings', `paymentAmount=${amount}&userId=${userid}`)
      .then(() => {
        getMerchant();
        handleClose();
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  const getMerchant = async () => {
    await cccClient
      .post('getMerchant', `userid=${userid}`)
      .then(function (response) {
        console.log(response);
        setFirstName(response.data.first_name);
        setLastName(response.data.last_name);
        setSupply(response.data.supply);
        setGain(response.data.gain);
        setBirthDate(response.data.birth_date);
        if (response.data.gender === 'F') {
          setGender('Female');
        } else if (response.data.gender === 'M') {
          setGender('Male');
        } else {
          setGender('Unknown');
        }
        setEmail(response.data.email);
        setPhone(response.data.phone);
        setAddress(response.data.address);
        setAmountDue(response.data.amount_due);
        setPurchasesTotal(response.data.purchases_total);
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  return (
    <div className={styles.container}>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <Typography alignSelf={'center'} variant="h4">
          Merchant Info
        </Typography>
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="FirstName:"
          variant="outlined"
          disabled
          value={firstName}
          onChange={(e) => setFirstName(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="LastName:"
          variant="outlined"
          disabled
          value={lastName}
          onChange={(e) => setLastName(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="Supply :"
          variant="outlined"
          disabled
          value={supply}
          onChange={(e) => setSupply(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="Gain :"
          variant="outlined"
          disabled
          value={gain}
          onChange={(e) => setGain(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="BirthDate :"
          variant="outlined"
          disabled
          value={birthDate}
          onChange={(e) => setBirthDate(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="Gender :"
          variant="outlined"
          disabled
          type="string"
          value={gender}
          onChange={(e) => setGender(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="Email :"
          variant="outlined"
          disabled
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="Address :"
          variant="outlined"
          disabled
          type="string"
          value={address}
          onChange={(e) => setAddress(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="Phone :"
          variant="outlined"
          disabled
          value={phone}
          onChange={(e) => setPhone(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="Amount Due :"
          variant="outlined"
          disabled
          value={amountDue}
          onChange={(e) => setAmountDue(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="Purchases Total :"
          variant="outlined"
          disabled
          value={purchasesTotal}
          onChange={(e) => setPurchasesTotal(e.target.value)}
        />{' '}
      </Box>
      <div className={styles.buttonContainer}>
        <Box p={3}>
          <Button type="primary" variant="contained" color="primary" onClick={() => handleOpen()}>
            Payment
          </Button>
        </Box>
        <Box p={3}>
          <Button
            variant="outlined"
            color="primary"
            onClick={() => navigate('/Merchant', { state: { userid: userid } })}>
            Back
          </Button>
        </Box>
      </div>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <Button variant="contained" color="error" onClick={() => deleteAccount()}>
          Delete Account
        </Button>
      </Box>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description">
        <div className={styles.modalContainer}>
          <TextField
            label="Amount :"
            variant="filled"
            value={amount}
            type="number"
            onChange={(e) => handleAmount(e.target.value)}
          />{' '}
          <Button type="submit" variant="contained" color="primary" onClick={() => makePayment()}>
            Pay
          </Button>
        </div>
      </Modal>
    </div>
  );
}

export default MerchantInfo;
