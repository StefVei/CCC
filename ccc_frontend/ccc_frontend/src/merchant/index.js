import React, { useState } from 'react';
import { Typography } from '@mui/material';
import {
  TextField,
  Button,
  Box
} from '@mui/material';
import useStyles from './styles';



// import { useNavigate } from 'react-router-dom';
 import { cccClient } from '../network';

function Merchant() {
  //   const navigate = useNavigate();
  const styles = useStyles();
  const [name, setName] = useState('');
  const [price, setPrice] = useState('');
	let userid = 5; 


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
				onchange={(e) => setName(e.target.value)}
				required
			/>{' '}
		</Box>
      	<Box pt={3}>
			<TextField 
				label="Product price:"
				variant="filled"
				value={price}
				onchange={(e) => setPrice(e.target.value)}
				required
			/>{' '}
		</Box>
		<Button type="submit" variant="contained" color="primary" onClick={() => addProduct()}>
            add Product
        </Button>
		<Button variant="contained" color="secondary" onClick={() => getProducts()}>
             get Products
        </Button>

		</div>
    </div>
  );
}

export default Merchant;
