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
        if (this.props.teamName === 'Select a Team') { //Only is displayed if a team has not been chosen yet
            return(<Card style={{width: '30vw'}} verticalAlign='middle' centered >
                        <CardContent>
                            <Header>{this.props.teamName}</Header>
                        </CardContent>
                    </Card>)
        }
        return(
            <div>
                <Card style={{width: "500px"}} centered >
                    <CardContent>
                        <Header>{this.props.teamName}</Header>
                        {this.props.info}
                    </CardContent>
                </Card>
            </div>

        )//End return(...)
    } //End render(){...}

};