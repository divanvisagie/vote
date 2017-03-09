import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import './index.css';
import '../node_modules/normalize.css/normalize.css'
import '../node_modules/milligram/dist/milligram.css'

import { Provider } from 'react-redux';
import { createStore } from 'redux';
import reducers from './reducers/combined';

let store = createStore(
  reducers, 
  window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
);

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>
  ,
  document.getElementById('root')
);
