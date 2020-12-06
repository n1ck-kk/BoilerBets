import React from "react";
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import {Header} from 'semantic-ui-react';
import {Link} from "react-router-dom";
import Typography from '@material-ui/core/Typography';


export default class TeamStatsCard extends React.Component{

    constructor(props){
        super(props);
        this.state={
            info: []
        }
    }

    async componentDidMount(){
        //console.log(this.props.post);
    }

    render() {
        if (this.props.teamName === 'Select A Team') { //Only is displayed if a team has not been chosen yet
            return(<Card style={{width: '30vw'}} verticalAlign='middle' centered >
                        <CardContent>
                            <Header>{this.props.teamName}</Header>
                        </CardContent>
                    </Card>)
        }
        else{
            return(
                <div>
                    <Card style={{width: "500px"}} centered >
                        <CardContent>
                            <Header>{this.props.teamName}</Header>
                            wins: {this.props.info.wins} <br/>
                            losses: {this.props.info.losses} <br/>
                            winPct: {this.props.info.winPct} <br/>
                            avg3PP: {this.props.info.avg3PP} <br/>
                            avgAST: {this.props.info.avgAST} <br/>
                            avgBLK: {this.props.info.avgBLK} <br/>
                            avgFGP: {this.props.info.avgFGP} <br/>
                            avgFTP: {this.props.info.avgFTP} <br/>
                            avgP: {this.props.info.avgP} <br/>
                            avgTO: {this.props.info.avgTO} <br/>
                            
                            
                        </CardContent>
                    </Card>
                </div>
            )//End return(...)
        }
    } //End render(){...}

};