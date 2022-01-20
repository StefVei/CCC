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
import { useNavigate, useLocation } from 'react-router-dom';
import { cccClient } from '../network';

function TransactionHistory() {
  const navigate = useNavigate();
  const { state } = useLocation();
  const { transactions, setTransactions } = useState([]);
  const { userid } = state;
  const styles = useStyles();

  useEffect(() => {
    getTransactions();
  }, []);

  const getTransactions = async () => {
    await cccClient
      .post('GetCitizenTransaction', `userId=${userid}`)
      .then(function (response) {
        console.log('🚀 ~ file: transactionHistory.js ~ line 30 ~ response', response);
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
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 650 }} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>
                  {' '}
                  <Typography variant="h6">Product id</Typography>
                </TableCell>
                <TableCell align="left">
                  <Typography variant="h6">Name</Typography>
                </TableCell>
                <TableCell align="left">
                  <Typography variant="h6">Quantity</Typography>
                </TableCell>
                <TableCell align="left">
                  <Typography variant="h6">Price</Typography>
                </TableCell>
                {/* <TableCell></TableCell> */}
              </TableRow>
            </TableHead>
            <TableBody>
              {transactions?.map((row) => (
                <TableRow key={row.name} sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
                  <TableCell component="th" scope="row">
                    {row.product_id}
                  </TableCell>
                  <TableCell align="left">{row.name}</TableCell>
                  <TableCell align="left">{row.quantity}</TableCell>
                  <TableCell align="left">{row.price}&nbsp;€</TableCell>
                  {/* <TableCell align="right">
                    <Button
                      type="primary"
                      variant="contained"
                      color="primary"
                      onClick={() => handleOpen(row)}>
                      Buy
                    </Button>
                  </TableCell> */}
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
