import React from 'react'
import { bindActionCreators } from 'redux'
import { connect } from 'react-redux'
import { getAll } from '../../modules/firma'

class Firme extends React.Component {
  props;

	constructor(props) {
		super(props)
	}

	componentWillMount() {
		this.props.getAll(this.props.access_token);
	}

	render() {
    console.log(this.props.firma.firme)
    if(this.props.firma.firme) {
      console.log(this.props.firma.firme[0])
      console.log(this.props.firma.firme[1])
    }
    let listItems = (this.props.firma.firme ? this.props.firma.firme.map( (f) => {
      return <li key={f.id}>{f.ime}</li>
    } ) : null);
    console.log(listItems)
  return  <div>
      <h1>Firme</h1>
      <ul>
        {listItems}
      </ul>
    </div>
  }
}

const mapStateToProps = ({ firma, login }) => ({
  firma: firma,
  access_token: login.access_token
})

const mapDispatchToProps = dispatch =>
  bindActionCreators(
    {
      getAll
    },
    dispatch
  )

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Firme)
