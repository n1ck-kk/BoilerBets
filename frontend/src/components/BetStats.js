import React, {Component} from 'react';
import Navbar from './Navbar.js';
import 'materialize-css';
import '../css/SignIn.css';
import '../css/TeamStats.css';
import '../css/Player.css';
import {Grid} from 'semantic-ui-react';
import BetAmount from './BetAmount.js';


export default class BetStats extends Component {

    constructor(props) {
        super(props);
        this.state = {
            betStatList: [],
        };
        this.handleGetBetStats = this.handleGetBetStats.bind(this);
        //this.handleClick = this.handleClick.bind(this);
    }

    async handleGetBetStats() {
        await fetch('http://localhost:8080/betStats/getAvailableBets' ,{
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            }
        }).then(response => response.text())
            .then(data => {
                console.log(data);
                console.log(JSON.parse(data));
                let t = JSON.parse(data);
                console.log(Object.values(t));
                // var index = 0;
                var i = 0;
                this.setState({betStatList: Object.values(JSON.parse(data))});
                
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
                <Grid style={{width: '100vw', height: '90vh'}} columns={2}>
                    <Grid.Column style={{width: '35vw', height: '80vh'}}> {/* Grid colummn for player list */}
                        <div className="scrollBox" style={{width: '40vw'}}>
                            {this.state.betStatList.map((betStatList, index) => {
                                    console.log(this.state.betStatList[index]);
                                    return(<BetAmount cardInfo={this.state.betStatList[index]} userInfo={this.props.location.state.username}/>)
                                }
                            )}
                        </div>
                    </Grid.Column>
                </Grid>
            </div>
        )
    }
} 
