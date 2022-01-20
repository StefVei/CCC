import React, { useState, useEffect } from 'react';
import { Typography, Box, TextField, Button, Modal } from '@mui/material';
import useStyles from './styles';
import { useNavigate, useLocation } from 'react-router-dom';
import { cccClient } from '../network';

function CompanyInfo() {
  const navigate = useNavigate();
  const { state } = useLocation();
  const { userid } = state;
  const styles = useStyles();
  const [name, setName] = useState('');
  const [establishmentDate, setEstablishmentDate] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [address, setAddress] = useState('');
  const [amountDue, setAmountDue] = useState('');
  const [creditLimit, setCreditLimit] = useState('');
  const [creditBalance, setCreditBalance] = useState('');
  const [dueDate, setDueDate] = useState('');
  const [open, setOpen] = useState(false);
  const [amount, setAmount] = useState(0);

  useEffect(() => {
    getCompany();
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

  const makePayment = async () => {
    await cccClient
      .post('makePendings', `paymentAmount=${amount}&userType='I'&citizenId=${userid}`)
      .then(() => {
        getCompany();
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  const getCompany = async () => {
    await cccClient
      .post('getCompany', `userid=${userid}`)
      .then(function (response) {
        setName(response.data.name);
        setEstablishmentDate(response.data.establishment_date);
        setEmail(response.data.email);
        setPhone(response.data.phone);
        setAddress(response.data.address);
        setAmountDue(response.data.amount_due);
        setCreditBalance(response.data.credit_balance);
        setCreditLimit(response.data.credit_limit);
        setDueDate(response.data.account_due_date);
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  return (
    <div className={styles.container}>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <Typography alignSelf={'center'} variant="h4">
          Company Info
        </Typography>
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="Name :"
          variant="outlined"
          disabled
          type="string"
          value={name}
          onChange={(e) => setAddress(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="Establishment Date :"
          variant="outlined"
          disabled
          type="string"
          value={establishmentDate}
          onChange={(e) => setAddress(e.target.value)}
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
          label="Credit limit :"
          variant="outlined"
          disabled
          value={creditLimit}
          onChange={(e) => setCreditLimit(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="Credit balance :"
          variant="outlined"
          disabled
          value={creditBalance}
          onChange={(e) => setCreditBalance(e.target.value)}
        />{' '}
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TextField
          label="Account due date :"
          variant="outlined"
          disabled
          value={dueDate}
          onChange={(e) => setDueDate(e.target.value)}
        />{' '}
      </Box>
      <div className={styles.buttonContainer}>
        <Box p={3}>
          <Button type="primary" variant="contained" color="primary" onClick={() => handleOpen()}>
            Payment
          </Button>
        </Box>
        <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
          <Button
            variant="outlined"
            color="primary"
            onClick={() => navigate('/Company', { state: { userid: userid } })}>
            Back
          </Button>
        </Box>
      </div>

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

export default CompanyInfo;
