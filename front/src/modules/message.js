export const MESSAGE_REQUESTED = 'message/MESSAGE_REQUESTED'
export const MESSAGE = 'message/MESSAGE'

const initialState = {
  isGettingMessage: false
}

export default (state = initialState, action) => {
  switch (action.type) {
    case MESSAGE_REQUESTED:
      return {
        ...state,
        isGettingMessage: true
      }

    case MESSAGE:
      return {
        ...state,
        content: action.content,
        isGettingMessage: false
      }

    default:
      return state
  }
}

export const getMessage = () => {
  return dispatch => {
    dispatch({
      type: MESSAGE_REQUESTED
    })

    return fetch('http://localhost:8082')
    		.then( response => response.text()  
        			,error => console.log('An error occurred.', error)
    		).then( text => {
	    			dispatch({
	    				type: MESSAGE,
	    				content: text
	    			})
    			}
    			,error => console.log('An error occurred.', error)
    		);
  }
}

