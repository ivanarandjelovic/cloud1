import React from 'react'
import { Field, reduxForm } from 'redux-form'

const LoginForm = props => (
        <form onSubmit={props.handleSubmit}>
          <div className="form-group">
            <label htmlFor="username">Username:</label> <Field name="username" component="input" type="text" className="form-control"/>
          </div>
            <div className="form-group">
            <label htmlFor="password">Password:</label> <Field name="password" component="input" type="password" className="form-control" />
          </div>
            <div className="form-group">
            <button type="submit" name="Login" className="btn btn-primary" >Login</button>
          </div>
        </form>
)

export default reduxForm({
    form: 'loginForm' // a unique name for this form
})(LoginForm);
