import React from 'react';
import { Button, Typography } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import Grid from '@mui/material/Grid';
import { cccClient } from '../network';

import useStyles from './styles';

function Home() {
  const navigate = useNavigate();
  const styles = useStyles();

  const GetMerchantOfTheMonth = async () => {
    await cccClient
      .post('SetMerchantOfTheMonth')
      .then(function (response) {
        console.log(response.data);
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  return (
    <div className={styles.container}>
      <Grid container direction="column" spacing={2} alignItems={'center'}>
        <Grid item xs={12}>
          <Typography alignSelf={'center'} variant="h2">
            Home
          </Typography>
        </Grid>
        <Grid item xs={12}>
          <Button variant="contained" color="primary" onClick={() => navigate('/CompanyRegister')}>
            Register Company
          </Button>
        </Grid>
        <Grid item xs={12}>
          <Button variant="contained" color="primary" onClick={() => navigate('/CitizenRegister')}>
            Register Citizen
          </Button>
        </Grid>
        <Grid item xs={12}>
          <Button variant="contained" color="primary" onClick={() => navigate('/MerchantRegister')}>
            Register Merchant
          </Button>
        </Grid>
        <Grid item xs={12}>
          <Button variant="contained" color="primary" onClick={() => navigate('/EmployeeRegister')}>
            Register Employee
          </Button>
        </Grid>
        <Grid item xs={12}>
          <Button variant="contained" color="primary" onClick={() => GetMerchantOfTheMonth()}>
            Merchant of the month
          </Button>
        </Grid>
        <Grid item xs={12}>
          <Button variant="contained" color="primary" onClick={() => navigate('/Login')}>
            Login
          </Button>
        </Grid>
      </Grid>
    </div>
  );
}

export default Home;
