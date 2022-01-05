import { makeStyles } from '@mui/styles';

const useStyles = makeStyles({
  container: {
    display: 'flex',
    justifyContent: 'center',
    alignItem: 'center'
  },
  textFieldContainer: {
    display: 'flex',
    flexDirection: 'column'
  },
  buttonContainer: {
    padding: '20px',
    display: 'flex',
    justifyContent: 'center',
    alignItem: 'center'
  }
});

export default useStyles;
