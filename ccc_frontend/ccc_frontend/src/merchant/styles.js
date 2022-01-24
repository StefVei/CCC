import { makeStyles } from '@mui/styles';

const useStyles = makeStyles({
  container: {
    display: 'flex',
    alignItem: 'center',
    justifyContent: 'center',
    flexDirection: 'column'
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
  buttonContainer: {
    display: 'flex',
    justifyContent: 'center',
    alignItem: 'center'
  },
  filterContainer: {
    display: 'flex',
    flexDirection: 'row',
    justifyContent: 'flex-start',
    alignItem: 'center'
  }
});

export default useStyles;
