export const FIRMA_REQUESTED = 'firma/REQUESTED'
export const FIRMA_ALL = 'firma/ALL'

const initialState = {
  requested: false
}

export default (state = initialState, action) => {
  switch (action.type) {
    case FIRMA_REQUESTED:
      return {
        ...state,
        requested: true
      }

    case FIRMA_ALL:
      return {
        ...state,
        firme: action.content,
        requested: false
      }

    default:
      return state
  }
}

export const getAll = (access_token) => {
  return dispatch => {
    dispatch({
      type: FIRMA_REQUESTED
    })

    let headers = new Headers();
    headers.set('Authorization', 'Bearer ' + access_token)
    headers.set('Content-Type', 'application/json')
    return fetch('/api/objekti/firma/all', {
        method: 'GET',
        headers: headers
      })
      .then(response => {
        if (response.ok) {
          return response.json()
        }
      }, error => console.log('An error occurred.', error))
      .then(json => {
        if (json) {
          dispatch({
            type: FIRMA_ALL,
            content: json
          })
        }
      }, error => console.log('An error occurred.', error));
  }
}
