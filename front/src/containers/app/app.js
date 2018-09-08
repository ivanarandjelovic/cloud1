import React from 'react'
import { Route, Link, withRouter } from 'react-router-dom'
import { bindActionCreators } from 'redux'
import { connect } from 'react-redux'
import Home from '../home/home'
import About from '../about/about'
import Login from '../login/login'
import Firme from '../firme/firme'
import { handleLogout, checkLogin } from '../../modules/login'

class App extends React.Component {
  props;

	constructor(props) {
		super(props)
	}

	componentWillMount() {
		this.props.checkLogin();
	}

	render() {
   return <div className="container">
    <header>
			<div>
				<ul className="nav">
	        <li className="nav-item"><Link to="/" className="nav-link">Home</Link></li>
          <li className="nav-item"><Link to="/firme" className="nav-link">Firme</Link></li>
	        <li className="nav-item"><Link to="/about-us" className="nav-link">About</Link></li>
				  <li className="nav-item"><button onClick={this.props.handleLogout} className="btn btn-warning  nav-link">Logout</button></li>
          <li className="nav-item">User: {this.props.user && this.props.user.name}</li>
				</ul>
		</div>
    </header>

	  {this.props.loggedIn ? (
			<main>
    		      <Route exact path="/" component={Home} />
              <Route exact path="/firme" component={Firme} />
    		      <Route exact path="/about-us" component={About} />
    		</main>
          ) : (
        	<main>
        	   <Login/>
        	</main>
          )
      }
    </div>;
  }
}

const mapStateToProps = ({ login, counter }) => ({
  loggedIn: login.loggedIn,
  count: counter.count,
  user: login.user
})

const mapDispatchToProps = dispatch =>
  bindActionCreators(
    {
			handleLogout,
			checkLogin
    },
    dispatch
  )

export default withRouter(connect(
  mapStateToProps,
  mapDispatchToProps
)(App))
// export default App
