import './App.css';
import React, {Component} from 'react';
import {BrowserRouter, Switch, Route} from 'react-router-dom';
import Popup from 'react-popup';
import SignIn from './components/SignIn.js'
import SignUp from './components/SignUp.js'
import Dashboard from './components/Dashboard.js';
import TeamStats from './components/TeamStats.js'
import { render } from '@testing-library/react';

class App extends Component {
  
  constructor(props) {
    super(props);
    this.state = {
      user: undefined
    }
  }

  render() {
    return (
      <BrowserRouter>
      <div className="App">
      <Popup />
          <Switch>
            <Route exact path ='/' component ={SignIn}/>
            <Route exact path ='/SignUp' component ={SignUp}/>
            <Route exact path ='/Dashboard' component ={Dashboard}/>
            <Route exact path ='/TeamStats' component = {TeamStats}/>
          </Switch>
      </div>
      </BrowserRouter>
    )
  }
}

export default App;
