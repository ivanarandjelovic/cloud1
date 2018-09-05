import React from 'react'
import { Route, Link } from 'react-router-dom'
import { bindActionCreators } from 'redux'
import { connect } from 'react-redux'
import Home from '../home/home'
import About from '../about/about'
import Login from '../login/login'
	
const App = props => (
  <div className="container">
    <header>
      <Link to="/">Home</Link>
      <Link to="/about-us">About</Link>
    </header>
   
	{props.isLoggedIn ? (
			<main>
    		      <Route exact path="/" component={Home} />
    		      <Route exact path="/about-us" component={About} />
    		</main>
          ) : (
        	<main>
        	   <Login/>
        	</main>
          )
    }	
  </div>
)

const mapStateToProps = ({ login, counter }) => ({
  login: login,
  count: counter.count
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
)(App)
// export default App
