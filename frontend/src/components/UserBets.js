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
import BetAmount from './BetAmount.js';


export default class UserBets extends Component {

    constructor(props) {
        super(props);
        this.state = {
            betStatList: [],
        };
        this.handleGetBetStats = this.handleGetBetStats.bind(this);
        //this.handleClick = this.handleClick.bind(this);
    }

    async handleGetBetStats() {
        await fetch('http://localhost:8080/userBets/getUserBets' ,{
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            }
        }).then(response => response.text())
            .then(data => {
                console.log(data);
                var index = 0;
                var i = 0;
                var tempArr = [];
                for (; i < data.length - 2; i++) {
                    //console.log(data.substr(i, 3));
                    if (data.substr(i, 3) == "}, " || data.substr(i, 3) == "} }") {
                        i+= 3;
                        tempArr.push(data.substr(index, i - index));
                        index = i;
                    }
                }
                this.setState({betStatList: tempArr});
                
                //console.log(this.state.playerList);
            }).catch(console.log("ERR"));
    }

    /* Current user info should be stored in this.props.history */
    async componentDidMount() {
        this.handleGetBetStats();
    }

    /* Once we have user bets, use map() to put them into a materialize table or somethin */
    render() {
        //console.log(this.props.location.state);
        console.log(this.state.betStatList[0]);
        console.log(this.state.betStatList[1]);
        console.log(this.props.location.state.username);
        return (
            <div className = "container"> {/* Section that displays the navbar */}
                <Navbar />
                <div className="scrollBox">
                    {this.state.betStatList.map((betStatList, index) => {
                            return(<BetAmount cardInfo={this.state.betStatList[index]} userInfo={this.props.location.state.username}/>)
                        }
                    )}
                </div>
            </div>
        )
    }
} 
