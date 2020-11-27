import React from "react";
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import CustomPopup from './CustomPopup';
import { config } from '../config/config.js';
import {Link} from "react-router-dom";


export default class PlayerCard extends React.Component{

    constructor(props){
        super(props);
        this.handleClick = this.handleClick.bind(this);
        this.state = {
            playerInfo: '',
            renderPopup: false
        }
    }

    /*
    async componentDidMount(){
        this.setState({
            className: this.props.className,
            classDesc: this.props.classDesc
        });
    }
    */

   async togglePopup(e) {
        e.preventDefault();
        this.setState({  
            playerInfo: '',
            renderPopup: false
        });  
    }

    /* Get team info from teamStats and render in popup */
    async handleClick (e) {
        e.preventDefault();
        console.log(this.props.playerId);
        await fetch('http://localhost:8080/player/getPlayerStats', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                playerId: this.props.playerId
            })
        }).then(res => res.text()).then((data) => {
            console.log(data);
            this.setState({
                playerInfo: data,
                renderPopup: true
            })
        }).catch(console.log)
    }

    render() {

        return(
           <div>
               <Button onClick = {this.handleClick} top-margin = "0px">
                    <Card style={{width: "500px"}} >
                        <CardContent>
                            <div className="left aligned">
                                {this.props.teamName}
                            </div>
                        </CardContent>
                    </Card>
               </Button>
               {this.state.renderPopup ? 
                    <CustomPopup 
                        text = {this.state.playerInfo}
                        closePopup = {this.togglePopup.bind(this)}
                    />
                : ''}
            </div>
        )//End return(...)
    } //End render(){...}

};