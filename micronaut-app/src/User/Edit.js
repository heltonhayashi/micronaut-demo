import { Paper, Button, Grid } from "@material-ui/core";
import React, { Component } from "react";
import TextField from "@material-ui/core/TextField";

import * as UserApi from "./Api";

const formName = "EditUserForm";

class Edit extends Component {
  constructor(props) {
    super(props);

    this.state = {
      userId: props.match.params.id
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  componentDidMount = async props => {
    const { userId } = this.state;
    if (userId) {
      const user = await UserApi.get(userId);
      this.setState(user);
    }
  };

  handleChange(event) {
    let change = {};
    change[event.target.id] = event.target.value;
    this.setState(change);
  }

  handleSubmit = async event => {
    try {
      event.preventDefault();
      console.log("salvando");
      let user = null;
      if (this.state.id) {
        user = await this.update();
      } else {
        user = await this.save();
      }
      this.setState({ saved: true });
      this.setState(user);
    } catch (error) {
      this.setState({ error_msg: error.message });
    }
  };

  update = async () => {
    try {
      return await UserApi.update(this.state.id, this.state);
    } catch (error) {
      throw new Error('Não foi possível atualizar o usuário');
    }
  };

  save = async () => {
    try {
      return await UserApi.save(this.state);
    } catch (error) {
      throw new Error('Não foi possível salvar o usuário');
    }
  };

  showMessage = message => {
    return <Paper>{this.state.deleted ? message : ""}</Paper>;
  };

  render() {
    return (
      <Paper style={{ padding: 20 }}>
        <form
          className={formName}
          autoComplete="off"
          onSubmit={this.handleSubmit}
        >
          <TextField
            id="name"
            label="Name"
            required
            value={this.state.name ? this.state.name : ""}
            onChange={this.handleChange}
          />
          <TextField
            id="email"
            label="Email"
            required
            value={this.state.email ? this.state.email : ""}
            onChange={this.handleChange}
          />
          <TextField
            id="password"
            label="Password"
            required
            value={this.state.password ? this.state.password : ""}
            onChange={this.handleChange}
          />
          <TextField
            id="birthdate"
            label="Birthdate"
            required
            value={this.state.birthdate ? this.state.birthdate : ""}
            onChange={this.handleChange}
          />
          <Grid item xs={12} style={{ marginTop: 10 }}>
            <Button variant="contained" color="primary" type="submit">
              Salvar
            </Button>
          </Grid>
          <Grid item xs={12} style={{ marginTop: 10 }}>
            {this.state.saved ? "Salvo com sucesso!" : ""}
            {this.state.error_msg ? this.state.error_msg : ""}
          </Grid>
        </form>
      </Paper>
    );
  }
}

export default Edit;
