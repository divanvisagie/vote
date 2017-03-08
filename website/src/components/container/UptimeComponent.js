import CounterDisplay from '../CounterDisplay';
import { connect } from 'react-redux';

const mapStateToProps = (state) => {
  return {
    text: state.uptime,
    prefix: 'Uptime'
  }
};
const mapDispatchToProps = (dispatch) => {
  setInterval(function() {
      dispatch({ type: 'UPTIME_INC' });
  },1000)
  return {}
};

const UptimeComponent = connect(
  mapStateToProps,
  mapDispatchToProps
)(CounterDisplay);

export default UptimeComponent;
