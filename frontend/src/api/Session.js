import { HttpInterface } from "./HttpInterface";

/**
 * Represents a login-session and contains the JSON
 * Web Token which where provided upon a successful login.
 */
class Session {
  /**
   * Creates a new instance of Session.
   *
   * @param {String} jwt The JWT received upon successful login.
   */
  constructor(jwt) {
    this.jwt = jwt;
    this.auth = false;
  }

  /**
   * Returns the JSON Web Token of the session.
   *
   * @return JWT authentication Token.
   */
  getJwt() {
    return String(this.jwt);
  }

  /**
   * Returns a boolean indicating whether the session
   * is authenticated.
   *
   * @return __true__ if authenticated, __false__ otherwise.
   */
  getAuth() {
    return Boolean(this.auth);
  }

  /**
   * Set the JSON Web Token of the session.
   *
   * @param {String} jwt The received JWT.
   */
  setJwt(jwt) {
    this.jwt = jwt;
  }

  /**
   * Set the boolean indicating whether the session
   * is authenticated or not.
   *
   * @param {Boolean} auth __true__ if authenticated, __false__ otherwise.
   */
  setAuth(auth) {
    this.auth = auth;
  }
}

export const SESSION = new Session("");
