import CounterDisplay from '../CounterDisplay';
import { connect } from 'react-redux';

const mapStateToProps = (state) => {
  return {
    text: state.counter,
    prefix: 'Uptime'
  }
};
const mapDispatchToProps = (dispatch) => {
  return {
    // onTodoClick: (id) => {
    //   dispatch(toggleTodo(id))
    // }
  }
};

const UptimeComponent = connect(
  mapStateToProps,
  mapDispatchToProps
)(CounterDisplay);

export default UptimeComponent;
