import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import User from '../User';
import Edit from '../User/Edit';

class Menu extends Component {
  render() {
    return (
      <Router>
        <div>
          <ul>
            <li>
              <Link to="/">Home</Link>
            </li>
            <li>
              <Link to="/users">User</Link>
            </li>
            <li>
              <Link to="/users/new">Add User</Link>
            </li>
          </ul>
          <hr />
          <Route exact path="/users" component={User} />
          <Route exact path="/users/new" component={Edit} />
          <Route exact path="/users/edit/:id" component={Edit} />
        </div>
      </Router>
    );
  }
}

export default Menu;
