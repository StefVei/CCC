import React, { useState } from 'react';
import {
  Typography,
  Box,
  TableContainer,
  Table,
  TableHead,
  TableRow,
  TableCell,
  TableBody,
  Paper
} from '@mui/material';
import useStyles from './styles';
// import { useNavigate } from 'react-router-dom';
import { cccClient } from '../network';

function ListCustomers() {
  //   const navigate = useNavigate();
  const [citizens, setCitizens] = useState([]);
  const [companies, setCompanies] = useState([]);

  const styles = useStyles();
  const getCitizens = async () => {
    await cccClient
      .post('getAllCitizens')
      .then(function (response) {
        setCitizens(response.data);
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  const getCompanies = async () => {
    await cccClient
      .post('getAllCompanies')
      .then(function (response) {
        setCompanies(response.data);
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  return (
    <div className={styles.container}>
      <div className={styles.textContainer}>
        <Typography alignSelf={'center'} variant="h4">
          Citizens
        </Typography>
      </div>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 650 }} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell align="left">First name</TableCell>
                <TableCell align="left">Last name</TableCell>
                <TableCell align="left">Email</TableCell>
                <TableCell align="left">Gender </TableCell>
                <TableCell align="left">Address</TableCell>
                <TableCell align="left">Phone</TableCell>
                <TableCell align="left">Vat</TableCell>
                <TableCell align="left">Amka</TableCell>
                <TableCell align="left">AmountDue</TableCell>
                <TableCell align="left">Credit Limit</TableCell>
                <TableCell align="left">Credit balance</TableCell>
                <TableCell align="left">Account due date</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {citizens.map((row) => (
                <TableRow key={row.name} sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
                  <TableCell component="th" scope="row">
                    {row.citizen_id}
                  </TableCell>
                  <TableCell align="left">{row.first_name}</TableCell>
                  <TableCell align="left">{row.last_name}</TableCell>
                  <TableCell align="left">{row.email}</TableCell>
                  <TableCell align="left">{row.gender}</TableCell>
                  <TableCell align="left">{row.address}</TableCell>
                  <TableCell align="left">{row.phone}</TableCell>
                  <TableCell align="left">{row.vat}</TableCell>
                  <TableCell align="left">{row.amka}</TableCell>
                  <TableCell align="left">{row.amount_due}</TableCell>
                  <TableCell align="left">{row.credit_limit}</TableCell>
                  <TableCell align="left">{row.credit_balance}</TableCell>
                  <TableCell align="left">{row.account_due_date}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Box>
      <div className={styles.textContainer}>
        <Typography alignSelf={'center'} variant="h5">
          Companies
        </Typography>
      </div>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 650 }} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>Product id</TableCell>
                <TableCell align="left">Company name</TableCell>
                <TableCell align="left">Establishment date</TableCell>
                <TableCell align="left">Email</TableCell>
                <TableCell align="left">Address</TableCell>
                <TableCell align="left">Phone</TableCell>
                <TableCell align="left">AmountDue</TableCell>
                <TableCell align="left">Credit Limit</TableCell>
                <TableCell align="left">Credit balance</TableCell>
                <TableCell align="left">Account due date</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {companies.map((row) => (
                <TableRow key={row.name} sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
                  <TableCell align="left">{row.name}</TableCell>
                  <TableCell align="left">{row.establishment_date}</TableCell>
                  <TableCell align="left">{row.email}</TableCell>
                  <TableCell align="left">{row.address}</TableCell>
                  <TableCell align="left">{row.phone}</TableCell>
                  <TableCell align="left">{row.amount_due}</TableCell>
                  <TableCell align="left">{row.credit_limit}</TableCell>
                  <TableCell align="left">{row.credit_balance}</TableCell>
                  <TableCell align="left">{row.account_due_date}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Box>
    </div>
  );
}

export default ListCustomers;
