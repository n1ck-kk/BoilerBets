import React from "react";
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import {Link} from "react-router-dom";
import Typography from '@material-ui/core/Typography';


export default class TeamStatsCard extends React.Component{

    constructor(props){
        super(props);
    }

    async componentDidMount(){
        //console.log(this.props.post);
    }

    render() {

        return(
            <div>
                <Card style={{width: "500px"}} centered >
                    <CardContent>
                        <Typography gutterright>
                            {this.props.teamName}
                        </Typography>
                    </CardContent>
                </Card>
            </div>

        )//End return(...)
    } //End render(){...}

};