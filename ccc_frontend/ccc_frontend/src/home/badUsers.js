import React, { useState, useEffect } from 'react';
import { Typography } from '@mui/material';
import { Button, Box } from '@mui/material';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import useStyles from './styles';
import { useNavigate } from 'react-router-dom';
import { cccClient } from '../network';

function TransactionHistory() {
  const navigate = useNavigate();
  const [badUsers, setBadUsers] = useState([]);
  const styles = useStyles();

  useEffect(() => {
    getBadUsers();
  }, []);

  const getBadUsers = async () => {
    await cccClient
      .post('BadUsers')
      .then(function (response) {
        setBadUsers(response.data);
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  return (
    <div className={styles.container}>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <Typography alignSelf={'center'} variant="h4">
          Bad Users
        </Typography>
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 650 }} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>
                  <Typography variant="h6">Name</Typography>
                </TableCell>
                {/* <TableCell align="left">
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
                </TableCell> */}
              </TableRow>
            </TableHead>
            <TableBody>
              {badUsers?.map((row) => (
                <TableRow key={row.name} sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
                  <TableCell component="th" scope="row">
                    {row.name}
                  </TableCell>
                  {/* <TableCell align="left">{row.quantity}</TableCell>
                  <TableCell align="left">{row.amount}&nbsp;€</TableCell>
                  <TableCell align="left">{row.date}</TableCell>
                  <TableCell align="left">{row.merchant_name}</TableCell>
                  <TableCell align="left">{row.employee_name}</TableCell>
                  <TableCell align="left">{row.type === 'A' ? 'Buyed' : 'Returned'}</TableCell> */}
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
