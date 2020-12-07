import React, {Component} from 'react';
import 'materialize-css';
import Navbar from './Navbar.js';
import '../css/SignIn.css';
import PlayerCard from './PlayerCard';
import '../css/TeamStats.css';
import {Grid, Button} from 'semantic-ui-react';
import '../css/Player.css';


export default class Players extends Component {

    constructor(props) {
        super(props);
        this.state = {
            playerList: [],
            playerName: 'Select A Player',
            teamName: '',
            position: '',
            playerNumber: '' //NEEDS TO BE ADDED... I AM NOT GETTING IT IN THE DATA RETURNED TO FRONT END
        };
        this.handleGetPlayers = this.handleGetPlayers.bind(this);
        this.handlePlayerClicked = this.handlePlayerClicked.bind(this);
        this.handleGetPlayerInfo = this.handleGetPlayerInfo.bind(this);
    }

    /* Once we have user bets, use map() to put them into a materialize table or somethin */
    render() {
        //console.log(this.props.location.state);
        console.log(this.state.playerList);
        var playerCardValue;
        if (this.state.playerInfo === undefined) {
            playerCardValue = 
            <PlayerCard playerName={this.state.playerName}/>
        }
        else {
            console.log(this.state.playerInfo);
            playerCardValue = 
            <PlayerCard teamName={this.state.teamName} playerName={this.state.playerName} position={this.state.position} keys={this.state.keys} values={this.state.values} />
        }

        return (
            <div className = "container"> {/* Section that displays the navbar */}
                <Navbar />
                <Grid style={{width: '100vw', height: '90vh'}} columns={2}>
                    <Grid.Column style={{width: '50vw', height: '80vh'}}> {/* Grid colummn for player list */}
                            <div className="scrollBox">
                                {this.state.playerList.map((playerList, index) => {
                                    console.log(playerList);
                                    console.log(this.state.playerList[index]);
                                    return( <Button style={{width: '20vw'}} horizontalAlign='middle' onClick={() => {this.handlePlayerClicked(this.state.playerList[index]);}}>
                                                {this.state.playerList[index]}
                                            </Button>)
                                    }
                                )}
                            </div>
                    </Grid.Column>

                    <Grid.Column style={{width: '50vw', height: '80vh'}} verticalAlign='middle' textAlgin='middle'> {/* Grid colummn for player stats once a player is clicked */}
                        {playerCardValue}
                    </Grid.Column>
                </Grid>
            </div>
        )
    }

    /* Get player info from playerStats and render in popup */
    async handleGetPlayerInfo (playerName) {
        console.log("HEREEEEEEEEE");
        console.log(this.state.playerName)
        console.log(playerName);
        
        await fetch('http://localhost:8080/player/getPlayerStats', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                playerId: parseInt(playerName) + 1
            })
        }).then(res => res.text()).then((data) => {
            console.log(data);
            console.log(JSON.parse(data));
            console.log(JSON.parse(data).teamName);
            console.log(JSON.parse(data).playerName);
            console.log(JSON.parse(data).playerStats);
            // console.log(JSON.parse(JSON.parse(data).playerStats));
            console.log(Object.keys(JSON.parse(data).playerStats));
            console.log(Object.values(JSON.parse(data).playerStats));
            console.log(Object.keys(JSON.parse(data).playerStats)[0]);
            console.log(Object.values(JSON.parse(data).playerStats)[0]);
            var temp = JSON.parse(data).playerStats.replace(/'/g,'"');
            console.log(JSON.parse(temp));
            console.log("KEYS: ");
            console.log(Object.keys(JSON.parse(JSON.parse(data).playerStats.replace(/'/g,'"'))));
            console.log(Object.values(JSON.parse(JSON.parse(data).playerStats.replace(/'/g,'"'))));




            this.setState({
                teamName: JSON.parse(data).teamName,
                playerName: JSON.parse(data).playerName,
                position: JSON.parse(data).position,
                playerInfo: JSON.parse(JSON.parse(data).playerStats.replace(/'/g,'"')),
                keys: Object.keys(JSON.parse(JSON.parse(data).playerStats.replace(/'/g,'"'))),
                values: Object.values(JSON.parse(JSON.parse(data).playerStats.replace(/'/g,'"')))
            })
        }).catch(console.log)
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

    async handlePlayerClicked(playerName) {
        this.handleGetPlayerInfo(playerName);
        this.setState({playerName: playerName})
    }
    
} 
