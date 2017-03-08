import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import './index.css';

import { Provider } from 'react-redux';
import { createStore } from 'redux';
import reducers from './reducers/combined';

let store = createStore(reducers);

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>
  ,
  document.getElementById('root')
);

setInterval(function() {
  store.dispatch({ type: 'INC' });
},1000)
