import React, { useState, useEffect } from 'react';
import { Typography, Button, Box, TextField } from '@mui/material';
import AdapterDay from '@mui/lab/AdapterDayjs';
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import DesktopDatePicker from '@mui/lab/DesktopDatePicker';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import useStyles from './styles';
import { useNavigate, useLocation } from 'react-router-dom';
import { cccClient } from '../network';

function TransactionHistory() {
  const navigate = useNavigate();
  const { state } = useLocation();
  const { userid } = state;
  const [transactions, setTransactions] = useState([]);
  const [fromDate, setFromDate] = useState(null);
  const [toDate, setToDate] = useState(null);
  const [fromAmount, setFromAmount] = useState(0);
  const [toAmount, setToAmount] = useState(20000);
  const styles = useStyles();

  useEffect(() => {
    getTransactions();
  }, []);

  const handleFromDate = (newValue) => {
    setFromDate(newValue);
  };

  const handleToDate = (newValue) => {
    setToDate(newValue);
  };

  const getTransactions = async () => {
    await cccClient
      .post('MerchantTransactions', `userId=${userid}`)
      .then(function (response) {
        setTransactions(response.data);
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  const handleSearch = async () => {
    await cccClient
      .post(
        'MerchantTransactions',
        `userId=${userid}&from=${fromDate.toISOString().slice(0, 10)}&to=${toDate
          .toISOString()
          .slice(0, 10)}&fromAmount=${fromAmount >= 0 ? fromAmount : 'null'}&toAmount=${
          toAmount >= 0 ? toAmount : 'null'
        }`
      )
      .then(function (response) {
        setTransactions(response.data);
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  return (
    <div className={styles.container}>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <Typography alignSelf={'center'} variant="h4">
          Transactions
        </Typography>
      </Box>
      <div className={styles.filterContainer}>
        <Box display="flex">
          <Box p={3} display="flex" alignItems="center">
            <Typography variant="body">Date :</Typography>
          </Box>
          <Box p={3}>
            <LocalizationProvider dateAdapter={AdapterDay}>
              <DesktopDatePicker
                label="From :"
                value={fromDate}
                onChange={handleFromDate}
                renderInput={(params) => <TextField {...params} />}
              />
            </LocalizationProvider>
          </Box>
          <Box p={3}>
            <LocalizationProvider dateAdapter={AdapterDay}>
              <DesktopDatePicker
                label="To :"
                value={toDate}
                onChange={handleToDate}
                renderInput={(params) => <TextField {...params} />}
              />
            </LocalizationProvider>
          </Box>
        </Box>
        <Box display="flex">
          <Box display="flex" alignItems="center">
            <Typography variant="body">Total Price :</Typography>
          </Box>
          <Box p={3}>
            <TextField
              label="From :"
              variant="filled"
              type="number"
              value={fromAmount}
              onChange={(e) => setFromAmount(e.target.value)}
            />
          </Box>
          <Box p={3}>
            <TextField
              label="To :"
              variant="filled"
              type="number"
              value={toAmount}
              onChange={(e) => setToAmount(e.target.value)}
            />
          </Box>
        </Box>
        <Box p={3} display="flex" alignItems="center">
          <Button
            type="primary"
            variant="contained"
            color="primary"
            onClick={() => {
              handleSearch();
            }}>
            Search
          </Button>
        </Box>
      </div>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 650 }} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>
                  <Typography variant="h6">Product Name</Typography>
                </TableCell>
                <TableCell align="left">
                  <Typography variant="h6">Quantity</Typography>
                </TableCell>
                <TableCell align="left">
                  <Typography variant="h6">Total Price</Typography>
                </TableCell>
                <TableCell align="left">
                  <Typography variant="h6">Date</Typography>
                </TableCell>
                <TableCell align="left">
                  <Typography variant="h6">Customer Name</Typography>
                </TableCell>
                <TableCell align="left">
                  <Typography variant="h6">Employee Name</Typography>
                </TableCell>
                <TableCell align="left">
                  <Typography variant="h6">Type</Typography>
                </TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {transactions?.map((row) => (
                <TableRow key={row.name} sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
                  <TableCell component="th" scope="row">
                    {row.product_name}
                  </TableCell>
                  <TableCell align="left">{row.quantity}</TableCell>
                  <TableCell align="left">{row.total_price}&nbsp;€</TableCell>
                  <TableCell align="left">{row.date}</TableCell>
                  <TableCell align="left">{row.customer_name}</TableCell>
                  <TableCell align="left">{row.employee_name ? row.employee_name : '-'}</TableCell>
                  <TableCell align="left">{row.type === 'A' ? 'Buyed' : 'Returned'}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Box>
      <Box p={1} sx={3} display="flex" justifyContent="center" alignItems="center">
        <Button variant="outlined" color="primary" onClick={() => navigate(-1)}>
          Back
        </Button>
      </Box>
    </div>
  );
}

export default TransactionHistory;
