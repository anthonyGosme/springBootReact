/*jshint esversion: 6 */
import React from 'react';
import CarList from './components/CarList';
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import './App.css';

function App() {

  return (
    <div className="App">
        <AppBar position="static" color="default">
        <Toolbar>
          <Typography variant="h6" color="inherit">
            CarList
          </Typography>
        </Toolbar>
      </AppBar>
      <CarList/>
    </div>
  );
}

export default App;