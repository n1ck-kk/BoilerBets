import React, {Component} from 'react';
import { withRouter } from 'react-router-dom';
import axios from 'axios';
import 'materialize-css';
import Navbar from './Navbar.js';
import { makeStyles } from '@material-ui/styles';
import '../css/SignIn.css';
import { Grid } from '@material-ui/core';
import TeamCard from './../TeamCard.js';
import TeamStatsCard from './../TeamStatsCard.js';


const useStyles = makeStyles((theme) => ({
    root: {
      flexGrow: 1,
    },
    menuButton: {
      marginRight: theme.spacing(2),
    },
    title: {
      flexGrow: 1,
    },
  }));

export default class TeamStats extends Component {

    constructor(props) {
        super(props);
        this.state = {
            teamList: ['Paul Miller', 'Nick Leuer']
        }
    }

    /* Use axios to get team stats from db here */
    /* Current user info should be stored in this.props.history */
    async getTeamStats() {

    }


    /* Once we have user bets, use map() to put them into a materialize table or somethin */
    render() {
        console.log(this.props.location.state);
        return (
            <div className = "container"> {/* Section that displays the navbar */}
                <Navbar />
                <div>
                    {this.state.teamList.map((teamList, index) => {
                            return(<TeamCard teamName={this.state.teamList[index]} />)
                        }
                    )}
                </div>
                <div>
                    {this.state.teamList.map((teamList, index) => {
                            return(<TeamStatsCard teamName={this.state.teamList[index]} />)
                        }
                    )}
                </div>
            </div>

            
                
                
        )
    }
}

//withRouter(Dashboard);
//withRouter(TeamStats);
