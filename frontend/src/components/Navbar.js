import React, {Component} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import { withRouter } from 'react-router-dom';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';
import { withStyles } from '@material-ui/core/styles';

const styles = theme => ({
  root: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
  },
});

class Navbar extends Component {

  constructor(props) {
    super(props);
    this.handleSignOut = this.handleSignOut.bind(this);
    this.state = {
      username: '',
      loggedIn: false
    }
  }

  handleSignOut(e) {
    e.preventDefault();
    this.setState({
      username: '',
      loggedIn: false
    })
    this.props.history.push('/');
  }

  render() {
    const { classes } = this.props;
    return (
        <div className={classes.root}>
            <AppBar position="static">
            <Toolbar>
                <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu">
                </IconButton>
                <Typography variant="h6" className={classes.title}>
                BoilerBets
                </Typography>
                <Button color="inherit">Players</Button>
                <Button color="inherit">Teams</Button>
                <Button color="inherit">Available Bets</Button>
                <Button color="inherit" onClick={this.handleSignOut}>Sign Out</Button>
            </Toolbar>
            </AppBar>
        </div>
      );
  }
}

//withRouter(Navbar);
export default withRouter(withStyles(styles)(Navbar));