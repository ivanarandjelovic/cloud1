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

export const getMessage = (access_token) => {
  return dispatch => {
    dispatch({
      type: MESSAGE_REQUESTED
    })

    let headers = new Headers();
    headers.set('Authorization', 'Bearer ' + access_token)
    return fetch('/servicea/', {
        method: 'GET',
        headers: headers
      })
      .then(response => {
        if (response.ok) {
          return response.text()
        }
      }, error => console.log('An error occurred.', error)).then(text => {
        if (text) {
          dispatch({
            type: MESSAGE,
            content: text
          })
        }
      }, error => console.log('An error occurred.', error));
  }
}
