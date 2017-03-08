import Stepper from '../Stepper';
import { connect } from 'react-redux';

const mapStateToProps = (state) => {
  return {
    text: state.stepper,
  }
};
const mapDispatchToProps = (dispatch) => {
  return {
    onClick() {
      dispatch({ type: 'STEPPER_INC' })
    }
  }
};

const StepperComponent = connect(
  mapStateToProps,
  mapDispatchToProps
)(Stepper);

export default StepperComponent;
