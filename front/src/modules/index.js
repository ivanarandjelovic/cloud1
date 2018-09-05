import { combineReducers } from 'redux'
import counter from './counter'
import message from './message'
import login from './login'

export default combineReducers({
  counter,
  message,
  login
})
