/*jshint esversion: 6 */

import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';

function CoolComponent({ adjective = 'Cool' }) {
    return <p>Youpi So {adjective} !</p>
  }
  
  ReactDOM.render(
    <div>
      <CoolComponent adjective="awesome" />
      <CoolComponent />
    </div>,
    document.getElementById('root')
  )