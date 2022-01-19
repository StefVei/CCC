import React, { useState, useEffect } from 'react';
import { Typography, Modal, TextField } from '@mui/material';
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

function ManageProducts() {
  const navigate = useNavigate();
  const { state } = useLocation();
  const { userid } = state;
  const styles = useStyles();
  const [open, setOpen] = useState(false);
  const [name, setName] = useState('');
  const [price, setPrice] = useState(0);
  const [quantity, setQuantity] = useState(0);
  const [totalPrice, setTotalPrice] = useState(0);
  const [products, setProducts] = useState([]);
  const [maxQuantity, setMaxQuantity] = useState(0);
  const [productid, setProductid] = useState(0);
  const [merchantId, setMerchantId] = useState(0);

  useEffect(() => {
    getProducts();
  }, []);

  const handleOpen = (prop) => {
    setName(prop.name);
    setPrice(prop.price);
    setQuantity(0);
    setOpen(true);
    setMaxQuantity(prop.quantity);
    setProductid(prop.product_id);
    setMerchantId(prop.merchant_id);
  };
  const handleClose = () => {
    setTotalPrice(0);
    setOpen(false);
  };

  const makeTransaction = async (quantity, productid) => {
    await cccClient
      .post(
        'MakeTrasnaction',
        `productId=${productid}&quantityOfBuyingProduct=${quantity}&userId=${userid}&merchantId=${merchantId}`
      )
      .then(function (response) {
        setProducts(response.data);
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  const getProducts = async () => {
    await cccClient
      .post('getAllProducts')
      .then(function (response) {
        setProducts(response.data);
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  const calculateTotalPrice = (e) => {
    if (e - maxQuantity - 1 != 0 && e > 0) {
      setTotalPrice(price * e);
      setQuantity(e);
    }
  };

  return (
    <div className={styles.container}>
      <Box p={3} sx={3} display="flex" justifyContent="center" alignItems="center">
        <Typography alignSelf={'center'} variant="h4">
          Products
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
                <TableCell></TableCell>
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
                  <TableCell align="right">
                    <Button
                      type="primary"
                      variant="contained"
                      color="primary"
                      onClick={() => handleOpen(row)}>
                      Buy
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
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description">
        <div className={styles.modalContainer}>
          <TextField label="Name :" variant="filled" value={name} disabled required />{' '}
          <TextField
            label="Price :"
            variant="filled"
            type="number"
            disabled
            value={price}
            required
          />{' '}
          <TextField
            label="Quantity:"
            variant="filled"
            type="number"
            value={quantity}
            onChange={(e) => calculateTotalPrice(e.target.value)}
            required
          />{' '}
          <TextField
            label="Total price :"
            variant="filled"
            type="number"
            disabled
            value={totalPrice}
            required
          />{' '}
          <Button
            type="submit"
            variant="contained"
            color="primary"
            onClick={() => makeTransaction(quantity, productid)}>
            Buy
          </Button>
        </div>
      </Modal>
    </div>
  );
}

export default ManageProducts;
