import React, {Component} from 'react';
import { withRouter } from 'react-router-dom';
import axios from 'axios';
import 'materialize-css';
import Navbar from './Navbar.js';
import { makeStyles } from '@material-ui/styles';
import '../css/SignIn.css';
import { Grid } from '@material-ui/core';
import PlayerCard from './PlayerCard.js';
import '../css/TeamStats.css';
import Button from '@material-ui/core/Button';
import '../css/Player.css';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import TeamStatsCard from './TeamStatsCard.js';


export default class Players extends Component {

    constructor(props) {
        super(props);
        this.state = {
            playerList: [],
        };
        this.handleGetPlayers = this.handleGetPlayers.bind(this);
        this.handleClick = this.handleClick.bind(this);
    }

    async handleClick() {
        this.props.history.push({
            pathname: '/AddPlayer',
            state: {
                username: this.props.location.state.username,
                loggedIn: true
            }
        });
    }

    async handleGetPlayers() {
        await fetch('http://localhost:8080/player/getAllPlayers' ,{
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            }
        }).then(response => response.json())
            .then(data => {
                console.log(data);
                this.setState({playerList: Object.keys(data)});
                //console.log(this.state.playerList);
            }).catch(console.log("ERR")); // here's how u set variables u want to use later
    }

    /* Current user info should be stored in this.props.history */
    async componentDidMount() {
        this.handleGetPlayers();
    }

    /* Once we have user bets, use map() to put them into a materialize table or somethin */
    render() {
        //console.log(this.props.location.state);
        console.log(this.state.playerList);
        return (
            <div className = "container"> {/* Section that displays the navbar */}
                <Navbar />
                <div className="scrollBox">
                    {this.state.playerList.map((playerList, index) => {
                        console.log(index);
                            return(<PlayerCard playerId={this.state.playerList[index]} />)
                        }
                    )}
                </div>
                <div className = "buttonPos">
                    <Button onClick = {this.handleClick} top-margin = "0px">
                        <Card style={{width: "500px"}} >
                            <CardContent>
                                <div className="left aligned">
                                    Add a new Player!
                                </div>
                            </CardContent>
                        </Card>
                    </Button>
                </div>
            </div>
        )
    }
} 
