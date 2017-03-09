import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import '../node_modules/normalize.css/normalize.css'
import '../node_modules/milligram/dist/milligram.css'
import './index.css';

import { Provider } from 'react-redux';
import { createStore } from 'redux';
import reducers from './reducers/combined';
import { Router, Route, Link, browserHistory, IndexRoute } from 'react-router';

import Login from './pages/Login';
import Ping from './pages/Ping';

let store = createStore(
  reducers, 
  window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
);

ReactDOM.render(   
  <Provider store={store}>
     <Router history={browserHistory}>
        <Route path='/' component={App}>
          <IndexRoute component={Login} />
          <Route path='login' component={Login} />
          <Route path='ping' component={Ping} />
        </Route>
    </Router>
  </Provider>
  ,
  document.getElementById('root')
);
