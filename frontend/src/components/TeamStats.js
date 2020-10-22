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
            teamList: []
        };
        this.handleGetTeamStats = this.handleGetTeamStats.bind(this);
    }
    /* Use axios to get team stats from db here */
    /* Current user info should be stored in this.props.history */
    async componentDidMount() {
        this.handleGetTeamStats();
    }


    /* Once we have user bets, use map() to put them into a materialize table or somethin */
    render() {
        console.log(this.props.location.state);
        return (
            <div className = "container"> {/* Section that displays the navbar */}
                <Navbar />
                <div>
                    {this.state.teamList.map((teamList, index) => {
                        console.log(index);
                            return(<TeamCard teamName={this.state.teamList[index]} />)
                        }
                    )}
                </div>
                {/* <div>
                    {this.state.teamList.map((teamList, index) => {
                            return(<TeamStatsCard teamName={this.state.teamList[index]} />)
                        }
                    )}
                </div> */}
            </div>

            
                
                
        )
    }

    async handleGetTeamStats() {
        console.log("HERE!!!");
        await fetch('http://localhost:8080/team/teamStats' ,{
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            }
        }).then(response => response.json())
            .then(data => {
                //console.log("setting state");
                //console.log(Object.keys(data));
                this.setState({teamList: Object.keys(data)});
                //console.log(this.state.teamList);
            }).catch(console.log("ERR")); // here's how u set variables u want to use later
    }
} //end default class TeamStats

//withRouter(Dashboard);
//withRouter(TeamStats);
