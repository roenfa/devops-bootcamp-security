import { Card ,CardContent} from '@mui/material';
import { makeStyles } from '@mui/styles';


const useStyles = makeStyles({
    root: {
        // fix content to center
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        height: '590px',
        // fix card to center
        
        maxWidth: '540px',
        borderRadius: '10px',
        boxShadow: '0px 0px 10px 0px rgba(0,0,0,0.35)',

    },
});

const SignCard = (props) => {
  
    const classes = useStyles();
    //set max width with props

    

  return (
    <Card variant="outlined" className={classes.root} style={{height:props.height}}>
      <CardContent style={{ width: '100%' ,alignItems:'center', justifyContent:'center', display:'flex'}}>
        {props.children}
      </CardContent>
    </Card>
  );
};

export default SignCard;