import React from 'react';
import { Typography } from '@mui/material';
import useStyles from './styles';
// import { useNavigate } from 'react-router-dom';
// import { cccClient } from '../network';

function Citizen() {
  //   const navigate = useNavigate();
  const styles = useStyles();

  return (
    <div className={styles.container}>
      <div className={styles.textContainer}>
        <Typography alignSelf={'center'} variant="h4">
          Citizen
        </Typography>
      </div>
    </div>
  );
}

export default Citizen;