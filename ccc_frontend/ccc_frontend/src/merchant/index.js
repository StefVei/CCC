import React, { useState, useEffect } from 'react';
import { Typography } from '@mui/material';
import { TextField, Button, Box } from '@mui/material';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import useStyles from './styles';
// import { useNavigate } from 'react-router-dom';
import { cccClient } from '../network';

function Merchant() {
  //   const navigate = useNavigate();
  const styles = useStyles();
  const [name, setName] = useState('');
  const [price, setPrice] = useState('');
  const [quantity, setQuantity] = useState(0);
  const [products, setProducts] = useState([]);
  let userid = 5;

  useEffect(() => {
    getProducts();
  }, []);

  const getProducts = async () => {
    await cccClient
      .post('getProducts', `userid=${userid}`)
      .then(function (response) {
        console.log(response);
        setProducts(response.data);
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  const addProduct = async () => {
    await cccClient
      .post('addProduct', `name=${name}&price=${price}&userid=${userid}`)
      .then(function (response) {
        console.log(response);
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  return (
    <div className={styles.container}>
      <div className={styles.textContainer}>
        <Typography alignSelf={'center'} variant="h4">
          Merchant
        </Typography>
        <Box pt={3}>
          <TextField
            label="Product Name:"
            variant="filled"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />{' '}
        </Box>
        <Box pt={3}>
          <TextField
            label="Price:"
            variant="filled"
            value={price}
            onChange={(e) => setPrice(e.target.value)}
            required
          />{' '}
        </Box>
        <Box pt={3}>
          <TextField
            label="Quantity:"
            variant="filled"
            type="number"
            value={quantity}
            onChange={(e) => setQuantity(e.target.value)}
            required
          />{' '}
        </Box>
        <Button type="submit" variant="contained" color="primary" onClick={() => addProduct()}>
          add Product
        </Button>
        <div>
          <TableContainer component={Paper}>
            <Table sx={{ minWidth: 650 }} aria-label="simple table">
              <TableHead>
                <TableRow>
                  <TableCell>Product id</TableCell>
                  <TableCell align="left">Name</TableCell>
                  <TableCell align="left">Quantity</TableCell>
                  <TableCell align="left">Price</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {products.map((row) => (
                  <TableRow
                    key={row.name}
                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
                    <TableCell component="th" scope="row">
                      {row.product_id}
                    </TableCell>
                    <TableCell align="left">{row.name}</TableCell>
                    <TableCell align="left">{row.quantity}</TableCell>
                    <TableCell align="left">{row.price}&nbsp;€</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </div>
      </div>
    </div>
  );
}

export default Merchant;
