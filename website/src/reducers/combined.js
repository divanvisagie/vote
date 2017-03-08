import { combineReducers } from 'redux';
//counter reducer
function uptime(state = 0, action) {
  switch (action.type) {
    case 'UPTIME_INC':
      state += 1;
      return state;
    default:
      return state;
  }
}

function stepper(state = 0, action) {
  switch (action.type) {
    case 'STEPPER_INC':
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
  uptime, stepper
});
