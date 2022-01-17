import React, { useState } from 'react';
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

	const getProducts = async () => {
		await cccClient
		.post(
			'getProducts',
			`userid=${userid}`)
			.then(function (response){
				console.log(response);
			
		})
		.catch(function (err){
			console.log(err);
		});
	};
 
  const addProduct = async () => {
	await cccClient
		.post(
			'addProduct',
			`name=${name}&price=${price}&userid=${userid}`)
			.then(function (response){
				console.log(response);
			
		})
		.catch(function (err){
			console.log(err);
		});
	};

  const [quantity, setQuantity] = useState(0);

  function createData(name, calories, fat, carbs, protein) {
    return { name, calories, fat, carbs, protein };
  }

  const rows = [
    createData('1', 'Product1', 1, 24.0),
    createData('2', 'Product2', 2, 37.0),
    createData('3', 'Product3', 2, 24.99),
    createData('4', 'Product4', 1, 67.0),
    createData('5', 'Product5', 3, 4.99)
  ];

	let userid = 5; 

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
        <Button type="submit" variant="contained" color="primary" onClick={() => handleSubmit()}>
          add Product
        </Button>
		<Button variant="contained" color="secondary" onClick={() => getProducts()}>
             get Products
        </Button>

		</div>
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
                {rows.map((row) => (
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
