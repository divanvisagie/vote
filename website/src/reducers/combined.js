import { combineReducers } from 'redux';

//counter reducer
function counter(state = 0, action) {
  console.log('counter',action);

  switch (action.type) {
    case 'INC':
      state += 1;
      console.log('counter returning', state);
      return state;
    default:
      console.log('returning default from counter reducer');
      return state;
  }
}

function boringReducer(state = 'empty', action) {
  //this is just an excuse to use combine
  return state;
}

export default combineReducers({
  counter, boringReducer
});
