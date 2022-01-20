import { makeStyles } from '@mui/styles';

const useStyles = makeStyles({
  container: {
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    alignItem: 'center'
  },
  buttonContainer: {
    display: 'flex',
    justifyContent: 'center',
    alignItem: 'center'
  },
  modalContainer: {
    display: 'flex',
    alignItem: 'center',
    justifyContent: 'center',
    flexDirection: 'column',
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    backgroundColor: 'white',
    border: '1px solid #000',
    boxShadow: 24,
    p: 4
  },
  textContainer: {
    display: 'flex',
    padding: '2.5%',
    flexDirection: 'column'
  }
});

export default useStyles;
