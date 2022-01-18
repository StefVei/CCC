import React from 'react';
import { Typography } from '@mui/material';
import useStyles from './styles';
// import { useNavigate } from 'react-router-dom';
// import { cccClient } from '../network';

function ListCustomers() {
  //   const navigate = useNavigate();
  const styles = useStyles();

  return (
    <div className={styles.container}>
      <div className={styles.textContainer}>
        <Typography alignSelf={'center'} variant="h4">
          Employee
        </Typography>
      </div>
    </div>
  );
}

export default ListCustomers;
