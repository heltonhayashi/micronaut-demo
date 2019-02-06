import React, { Component, Fragment } from "react";
import Table from "@material-ui/core/Table";
import {
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Paper,
  Button,
  Grid
} from "@material-ui/core";
import { Link } from "react-router-dom";

import * as UserApi from "./Api";

class User extends Component {
  constructor(props) {
    super(props);
    this.state = { users: [] };
  }

  componentDidMount = async () => {
    const users = await UserApi.getAll();
    this.setState({ users });
  };

  handleDelete = async id => {
    await UserApi.exclude(id);
    const users = await UserApi.getAll();
    this.setState({ users });
    this.setState({ deleted: true });
  };

  showMessage = (message) => {
    return (
      <Paper>
        {this.state.deleted ? message : ""}
      </Paper>
    )
  }

  render() {
    const { users } = this.state;
    return (
      <Fragment>
        {this.state.deleted ? this.showMessage("Excluído com sucesso!") : ""}
        <Paper>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>ID</TableCell>
                <TableCell align="right">Name</TableCell>
                <TableCell align="right">Email</TableCell>
                <TableCell align="right">Password</TableCell>
                <TableCell align="right">Birthdate</TableCell>
                <TableCell align="right" />
                <TableCell align="left">Ações</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {users.map(row => (
                <TableRow key={row.id}>
                  <TableCell component="th" scope="row">
                    {row.id}
                  </TableCell>
                  <TableCell align="right">{row.name}</TableCell>
                  <TableCell align="right">{row.email}</TableCell>
                  <TableCell align="right">{row.password}</TableCell>
                  <TableCell align="right">{row.birthdate}</TableCell>
                  <TableCell align="right" />
                  <TableCell align="left">
                    <Button color="primary">
                      <Link to={{ pathname: `/users/edit/${row.id}` }}>edit</Link>
                    </Button>
                    <Button
                      color="secondary"
                      onClick={() => this.handleDelete(row.id)}
                    >
                      delete
                    </Button>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </Paper>
      </Fragment>
    );
  }
}

export default User;
