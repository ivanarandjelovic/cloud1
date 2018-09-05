import React from 'react'
import { bindActionCreators } from 'redux'
import { connect } from 'react-redux'

const Login = props => (
		<div className="row">
  <div className="offset-md-4 col-md-4">
      <h1 >Login</h1>
      <p>Here comes login form ...</p>
	    <form>
	    	<div className="form-group">
	    		<label htmlFor="username">Username:</label> <input type="text" className="form-control" name="username"/>
	    	</div>
	        <div className="form-group">
	    		<label htmlFor="password">Password:</label> <input type="password" className="form-control" name="password" />
	    	</div>
	        <div className="form-group">
	    		<input type="submit" name="Login" className="btn btn-primary" value="Login"/>
	    	</div>
	    </form>
  </div>
  </div>
)

const mapStateToProps = ({ login }) => ({
  login: login
})

const mapDispatchToProps = dispatch =>
  bindActionCreators(
    {
      
    },
    dispatch
  )

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Login)
