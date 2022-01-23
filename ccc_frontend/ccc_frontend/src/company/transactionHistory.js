import React, { useState, useEffect } from 'react';
import {
  Typography,
  Button,
  Box,
  TextField,
  Select,
  MenuItem,
  Collapse,
  ToggleButton
} from '@mui/material';
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

const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 250
    }
  }
};

function TransactionHistory() {
  const navigate = useNavigate();
  const { state } = useLocation();
  const { userid } = state;
  const [isChecked, setIsChecked] = useState(false);
  const [transactions, setTransactions] = useState([]);
  const [fromDate, setFromDate] = useState(null);
  const [toDate, setToDate] = useState(null);
  const [fromAmount, setFromAmount] = useState(0);
  const [toAmount, setToAmount] = useState(0);
  const [personName, setPersonName] = React.useState([]);
  const [employees, setEmployees] = useState([]);
  const styles = useStyles();

  useEffect(() => {
    getTransactions();
    getEmployees();
  }, []);

  const handleFromDate = (newValue) => {
    setFromDate(newValue);
  };

  const handleToDate = (newValue) => {
    setToDate(newValue);
  };

  const handleClear = () => {
    setPersonName([]);
    setFromDate(null);
    setToDate(null);
    setFromAmount(0);
    setToAmount(0);
    setIsChecked(false);
    getTransactions();
  };

  const getEmployees = async () => {
    await cccClient
      .post('getEmployees', `userid=${userid}`)
      .then(function (response) {
        setEmployees(response.data);
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  const handleChange = (event) => {
    const {
      target: { value }
    } = event;
    setPersonName(typeof value === 'string' ? value.split(',') : value);
  };

  const handleSearch = async () => {
    await cccClient
      .post(
        'CompanyTransactions',
        `userId=${userid}&fromDate=${
          fromDate ? fromDate.toISOString().slice(0, 10) : 'null'
        }&toDate=${toDate ? toDate.toISOString().slice(0, 10) : 'null'}&employeesList=${
          personName.length > 0 ? personName.toString() : 'null'
        }&fromAmount=${fromAmount ? fromAmount : 'null'}&toAmount=${toAmount ? toAmount : 'null'}`
      )
      .then(function (response) {
        setTransactions(response.data);
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  const getTransactions = async () => {
    await cccClient
      .post('CompanyTransactions', `userId=${userid}`)
      .then(function (response) {
        setTransactions(response.data);
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  const returnProduct = async (prop) => {
    await cccClient
      .post('ReturnProduct', `userId=${userid}&transactionId=${prop.transaction_id}`)
      .then(function () {
        getTransactions();
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
        <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
          <ToggleButton
            value="check"
            selected={isChecked}
            onChange={() => {
              setIsChecked(!isChecked);
            }}>
            <Typography variant="body"> Filter</Typography>
          </ToggleButton>
        </Box>
        <Collapse in={isChecked}>
          <Box
            p={3}
            sx={3}
            display="flex"
            justifyContent="flex-start"
            alignItems="center"
            flexDirection={'column'}>
            <Box display="flex">
              <Box display="flex" alignItems="center">
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
                <Typography variant="body">Amount due :</Typography>
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
            <Box p={3}>
              <Typography variant="body">Employees :</Typography>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                value={personName}
                multiple
                label="Employees"
                onChange={handleChange}
                MenuProps={MenuProps}
                sx={{ m: 1, width: 300 }}>
                {employees.map((row) => (
                  <MenuItem key={row.employee_id} value={row.employee_id}>
                    <Typography>
                      {row.first_name} {row.last_name}
                    </Typography>
                  </MenuItem>
                ))}
              </Select>
            </Box>
            <Box display="flex">
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
              <Box p={3} display="flex" alignItems="center">
                <Button
                  type="primary"
                  variant="outlined"
                  color="primary"
                  onClick={() => {
                    handleClear();
                  }}>
                  Clear
                </Button>
              </Box>
            </Box>
          </Box>
        </Collapse>
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
                  <Typography variant="h6">Merchant Name</Typography>
                </TableCell>
                <TableCell align="left">
                  <Typography variant="h6">Employee Name</Typography>
                </TableCell>
                <TableCell align="left">
                  <Typography variant="h6">Type</Typography>
                </TableCell>
                <TableCell></TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {transactions?.map((row) => (
                <TableRow key={row.name} sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
                  <TableCell component="th" scope="row">
                    {row.product_name}
                  </TableCell>
                  <TableCell align="left">{row.quantity}</TableCell>
                  <TableCell align="left">{row.amount}&nbsp;€</TableCell>
                  <TableCell align="left">{row.date}</TableCell>
                  <TableCell align="left">{row.merchant_name}</TableCell>
                  <TableCell align="left">{row.employee_name}</TableCell>
                  <TableCell align="left">{row.type === 'A' ? 'Buyed' : 'Returned'}</TableCell>
                  <TableCell align="right">
                    <Button
                      type="primary"
                      variant="contained"
                      color="primary"
                      disabled={row.type === 'E'}
                      onClick={() => {
                        returnProduct(row);
                      }}>
                      return
                    </Button>
                  </TableCell>
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
