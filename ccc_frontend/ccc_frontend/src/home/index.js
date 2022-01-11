import React from 'react';
import { Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import Grid from '@mui/material/Grid';

import useStyles from './styles';

function Home() {
  const navigate = useNavigate();
  const styles = useStyles();

  return (
    <div className={styles.container}>
      <Grid container direction="column" spacing={2}>
        <Grid item xs={6}>
          <Button variant="contained" color="primary" onClick={() => navigate('/CompanyRegister')}>
            Register Company
          </Button>
        </Grid>
        <Grid item xs={6}>
          <Button variant="contained" color="primary" onClick={() => navigate('/CitizenRegister')}>
            Register Citizen
          </Button>
        </Grid>
      </Grid>
    </div>
  );
}

export default Home;
