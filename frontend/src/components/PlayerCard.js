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
        if (this.props.playerName === 'Select A Player') { //Only is displayed if a team has not been chosen yet
            return(<Card style={{width: '30vw'}} verticalAlign='middle' centered>
                        <CardContent color='grey'>
                            <Header>{this.props.playerName}</Header>
                        </CardContent>
                    </Card>)
        }
        else{ //Else a team has been selected
            return(
                <div>
                    <Card style={{width: "30vw"}} centered >
                        <CardContent>
                            <Header>{this.props.playerName}</Header>
                            {this.props.teamName}
                                {this.props.info}
                        </CardContent>
                    </Card>
                </div>
            )//End return(...)
        }
    } //End render(){...}

};