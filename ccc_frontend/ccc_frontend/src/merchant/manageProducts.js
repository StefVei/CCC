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
import Modal from '@mui/material/Modal';
import useStyles from './styles';
import { useLocation, useNavigate } from 'react-router-dom';
import { cccClient } from '../network';

function ManageProducts() {
  const navigate = useNavigate();
  const { state } = useLocation();
  const { userid } = state;
  const styles = useStyles();
  const [name, setName] = useState('');
  const [price, setPrice] = useState('');
  const [quantity, setQuantity] = useState(0);
  const [products, setProducts] = useState([]);
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  useEffect(() => {
    getProducts();
  }, []);

  const getProducts = async () => {
    await cccClient
      .post('getProducts', `userid=${userid}`)
      .then(function (response) {
        setProducts(response.data);
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  const addProduct = async () => {
    await cccClient
      .post('addProduct', `name=${name}&price=${price}&quantity=${quantity}&userid=${userid}`)
      .then(function () {
        getProducts();
        handleClose();
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  return (
    <div className={styles.container}>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <Typography alignSelf={'center'} variant="h4">
          Merchant Products
        </Typography>
      </Box>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description">
        <div className={styles.modalContainer}>
          <TextField
            label="Product Name:"
            variant="filled"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />{' '}
          <TextField
            label="Price:"
            variant="filled"
            type="number"
            value={price}
            onChange={(e) => setPrice(e.target.value)}
            required
          />{' '}
          <TextField
            label="Quantity:"
            variant="filled"
            type="number"
            value={quantity}
            onChange={(e) => setQuantity(e.target.value)}
            required
          />{' '}
          <Button type="submit" variant="contained" color="primary" onClick={() => addProduct()}>
            add
          </Button>
        </div>
      </Modal>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
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
                <TableRow key={row.name} sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
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
      </Box>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <Button variant="contained" color="primary" onClick={() => handleOpen()}>
          add Product
        </Button>
      </Box>
      <Box p={1} sx={3} display="flex" justifyContent="center" alignItems="center">
        <Button
          variant="outlined"
          color="primary"
          onClick={() => navigate('/Merchant', { state: { userid: userid } })}>
          Back
        </Button>
      </Box>
    </div>
  );
}

export default ManageProducts;
