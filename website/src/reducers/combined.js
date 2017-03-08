import { combineReducers } from 'redux';
//counter reducer
function counter(state = 0, action) {
  switch (action.type) {
    case 'INC':
      state += 1;
      return state;
    default:
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
