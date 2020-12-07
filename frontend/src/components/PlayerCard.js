import React from "react";
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import {Header} from 'semantic-ui-react';
import Button from '@material-ui/core/Button';
import CustomPopup from './CustomPopup';
import { config } from '../config/config.js';
import {Link} from "react-router-dom";


export default class PlayerCard extends React.Component{

    constructor(props){
        super(props);
    }

    render() {
    console.log('IN Player Card');
    
        if (this.props.playerName === 'Select A Player' || this.props.values === undefined) { //Only is displayed if a team has not been chosen yet
            return(<Card style={{width: '30vw'}} verticalAlign='middle' centered>
                        <CardContent color='grey'>
                            <Header>{this.props.playerName}</Header>
                        </CardContent>
                    </Card>)
        }
        else{ //Else a team has been selected
            // console.log(this.props.info[0]);
            console.log(this.props.keys);
            console.log(this.props.values[0]);

            return(
                <div>
                    <Card style={{width: "30vw"}} centered >
                        <CardContent>
                            <Header>{this.props.playerName}: {this.props.teamName}</Header>
                                Position: {this.props.position} <br/>
                                Average Assists: {this.props.values[2]} <br/>
                                Average Blocks: {this.props.values[3]} <br/>
                                Average Field Goals: {this.props.values[7]} <br/>
                                Average 3-Point Field Goals: {this.props.values[8]} <br/>
                                Average Free Throws: {this.props.values[9]} <br/>
                                Average Minutes: {this.props.values[6]} <br/>
                                Average Points: {this.props.values[1]} <br/>
                                Average Steals: {this.props.values[4]} <br/>
                                Average Turn Overs: {this.props.values[5]} <br/>
                        </CardContent>
                    </Card>
                </div>
            )//End return(...)
        }
    } //End render(){...}

};