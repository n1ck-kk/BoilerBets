import './App.css';
import React, {Component} from 'react';
import {BrowserRouter, Switch, Route} from 'react-router-dom';
import SignIn from './components/SignIn.js'
import SignUp from './components/SignUp.js'
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
          <Switch>
            <Route exact path ='/' component ={SignIn}/>
            <Route exact path ='/SignUp' component ={SignUp}/>
          </Switch>
      </div>
      </BrowserRouter>
    )
  }
}

export default App;
