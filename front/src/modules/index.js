import { combineReducers } from 'redux'
import counter from './counter'
import message from './message'

export default combineReducers({
  counter,
  message
})
