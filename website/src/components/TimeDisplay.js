import React, { PropTypes } from 'react'

const TimeDisplay = ({ time }) => (
  <div>
    <h2>{time}</h2>
  </div>
)

TimeDisplay.propTypes = {
  time: PropTypes.string.isRequired
}

export default TimeDisplay
