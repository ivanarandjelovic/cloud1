import React from 'react'
import { bindActionCreators } from 'redux'
import { connect } from 'react-redux'
import LoginForm from './loginForm'
import { submitLogin } from '../../modules/login'

const Login = props => (
  <div className="row">
	  <div className="offset-md-4 col-md-4">
	      <h1 >Login</h1>
	      <p>Here comes login form ...</p>
		    <LoginForm onSubmit={props.submitLogin}/>
	  </div>
  </div>
)

const mapStateToProps = ({ login }) => ({
  login: login
})

const mapDispatchToProps = dispatch =>
  bindActionCreators(
    {
      submitLogin
    },
    dispatch
  )

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Login)
