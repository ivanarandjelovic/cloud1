export const LOGIN_REQUESTED = 'login/LOGIN_REQUESTED'
export const LOGIN_SUCCESS = 'login/LOGIN_SUCCESS'
export const LOGIN_FAILED = 'login/LOGIN_FAILED'
export const LOGOUT_REQUESTED = 'login/LOGOUT_REQUESTED'

const initialState = {
  loggedIn: false
}

export default (state = initialState, action) => {
  switch (action.type) {
   case LOGIN_REQUESTED:
     return {
       ...state,
       inProgress: true
     }
     case LOGOUT_REQUESTED:
       localStorage.removeItem('access_token')
       return {
         ...state,
         inProgress: false,
         loggedIn: false,
         access_token: null
       }
     case LOGIN_SUCCESS:
       localStorage.setItem('access_token',action.access_token)
       return {
         ...state,
         inProgress: false,
         loggedIn: true,
         access_token: action.access_token
       }
       case LOGIN_FAILED:
         return {
           ...state,
           inProgress: false,
           loggedIn: false
         }
    default:
      return state
  }
}

export const handleLogout = values => {
  return dispatch => {
    dispatch({
      type: LOGOUT_REQUESTED
    })
  }
}

export const checkLogin = () => {
  var access_token = localStorage.getItem('access_token')
  if(access_token) {
    return dispatch =>  {
      dispatch({
        type: LOGIN_SUCCESS,
        access_token
      })
    }
  } else {
    return dispatch => {}
  }
}

export const submitLogin = values => {
  return dispatch => {
    dispatch({
      type: LOGIN_REQUESTED
    })

      let headers = new Headers();
      headers.set('Authorization', 'Basic ' + btoa('public:'))
      //headers.set( 'Content-Type', 'application/json; charset=utf-8')
      headers.set( 'Content-Type', 'application/x-www-form-urlencoded')
      // Now attempt to login ...
       return fetch('/oauth/token', {
         method: 'POST',
         headers: headers,
         body: "username="+values.username+"&password="+values.password+"&grant_type=password&scope=test_scope"
       })
       		.then( response => response.json()
        ).then( json => {
    	    			dispatch({
    	    				type: LOGIN_SUCCESS,
    	    				access_token: json.access_token
    	    			})
       			}
       			,error => {
              console.log('An error occurred.', error)
              dispatch({
                type: LOGIN_FAILED
              })
            }
       		);

  }
}
