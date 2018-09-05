import { combineReducers } from 'redux'
import counter from './counter'
import message from './message'
import login from './login'
import { reducer as formReducer } from 'redux-form'

export default combineReducers({
  form: formReducer,
  counter,
  message,
  login
})
