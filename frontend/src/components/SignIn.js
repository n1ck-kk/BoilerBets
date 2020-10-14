import React, {Component} from 'react';
import {Redirect} from 'react-router-dom';
import {Link} from 'react-router-dom'
import axios from 'axios';
import 'materialize-css';
import '@material-ui/core';
import { Person, Book } from '@material-ui/icons';
import '../css/SignIn.css';

class SignIn extends Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: ''
        }
    }

    
    handleChange =(e)=>{
        this.setState({
            [e.target.id]: e.target.value
        })
    }

    handleRedirect = (e) =>{
        e.preventDefault();
        this.props.history.push('/SignUp');
    }

    /* Here we need to check the username with backend and then log the user in, redirect to dashboard */
    handelSubmit =(e)=>{
        e.preventDefault();
        this.props.history.push('/Dashboard');
    }

    render() {
        return (
            <div className = "container">
                <div className = "centered">
                    <div className="col s6">
                        <form onSubmit={this.handelSubmit}>
                            <div className="input-field col s12">
                                <Person />
                                <input type="text" id="username" placeholder="Username" onChange = {this.handleChange} />
                            </div>
                            <div className="input-field col s12">
                                <Book />
                                <input type="password" id="password" placeholder="Password" onChange = {this.handleChange} />
                            </div>
                            <div className= "spacer"></div>
                            <button className = "btn btn-primary" type="submit" onSub>Log in</button>
                        </form>
                        <div className= "spacer"></div>
                        <Link to="/SignUp" className = "btn btn-primary">Sign Up</Link>
                    </div>
                </div>
            </div>
        )
    }
}

export default SignIn;
