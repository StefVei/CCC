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

function BadUsers() {
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
        console.log(response.data);
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  return (
    <div className={styles.container}>
      <Box p={3} sx={3} display="flex" justifyContent="center">
        <Typography alignSelf={'center'} variant="h4">
          Bad Users
        </Typography>
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center">
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 650 }} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell align="left">
                  <Typography variant="h6">Type</Typography>
                </TableCell>
                <TableCell align="left">
                  <Typography variant="h6">Name</Typography>
                </TableCell>
                <TableCell align="left">
                  <Typography variant="h6">Email</Typography>
                </TableCell>
                <TableCell align="left">
                  <Typography variant="h6">Phone</Typography>
                </TableCell>
                <TableCell align="left">
                  <Typography variant="h6">Amount Due</Typography>
                </TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {badUsers?.map((row) => (
                <TableRow key={row.name} sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
                  <TableCell align="left">{row.type}</TableCell>
                  <TableCell align="left">
                    {row.type === 'Company' ? row.name : row.first_name + ' ' + row.last_name}
                  </TableCell>
                  <TableCell align="left">{row.email}</TableCell>
                  <TableCell align="left">{row.phone}</TableCell>
                  <TableCell align="left">{row.amount_due}</TableCell>
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

export default BadUsers;
