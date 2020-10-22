import React from "react";
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import {Link} from "react-router-dom";


export default class TeamCard extends React.Component{

    constructor(props){
        super(props);
    }

    async componentDidMount(){
        //console.log(this.props.post);
        this.setState({
            className: this.props.className,
            classDesc: this.props.classDesc
        });
    }

    render() {

        return(
            <div>
                
                <Card style={{width: "500px"}} centered >
                    <CardContent>
                        <div className="left aligned">
                            {this.props.teamName}
                        </div>
                    </CardContent>
                </Card>
            </div>

        )//End return(...)
    } //End render(){...}

};