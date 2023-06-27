import { useState } from "react";
import { ActiveLink } from "../../components/Navigation/ActiveLink";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faRightToBracket, faUser } from "@fortawesome/free-solid-svg-icons";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import "./Login.css";
import { useNavigate } from "react-router-dom";
import { HttpInterface, SESSION } from "../../api/HttpInterface";

/**
 * Form component representing the login form.
 *
 * @component
 * @param {Object} props - Component props
 * @param {function} props.setUser - Callback function to set the user data after successful login
 * @returns  {JSX.Element}
 */
export function Login(props) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const navigate = useNavigate();

  /**
   * Handles form submission by sending the authentication request to the server.
   *
   * @param {Object} event - Form submission event
   */
  async function submitForm(event) {
    event.preventDefault(); // Prevent default form submission
    console.log("Submitting form");
    await HttpInterface.authenticateLogin({username: username, password: password});

    if (SESSION.Authorized) {
      setIsLoggedIn(true);
      navigate("/");
    } else {
      setUsername("");
      setPassword("");
    }
  }


  let errorMessage = null;
  if (error) {
    errorMessage = <p className="error">{error}</p>;
  }

  /** Username input with parameters */
  const USERNAME_INPUT = (
    <TextField
      variant="filled"
      label="Username"
      defaultValue=""
      value={username}
      onChange={(event) => setUsername(event.target.value)}
    />
  );

  /** Password input with needed parameters */
  const PASSWORD_INPUT = (
    <TextField
      variant="filled"
      label="Password"
      type="password"
      defaultValue=""
      value={password}
      onChange={(event) => setPassword(event.target.value)}
    />
  );

  /** The login icon which is going to be in the login button */
  const LOGIN_ICON = (
    <FontAwesomeIcon icon={faRightToBracket} style={{ color: "#ffffff" }} />
  );

  /** Log in button with the given login icon */
  const LOGIN_BUTTON = (
    <Button id="login-btn" variant="contained" onClick={submitForm}>
      Login &nbsp; {LOGIN_ICON}
    </Button>
  );

  /** Signup icon which is going to be at the create account button */
  const SIGNUP_ICON = (
    <FontAwesomeIcon icon={faUser} style={{ color: "#ffffff" }} />
  );

  /** Signup button consisting of the signup icon */
  const SIGNUP_BUTTON = (
    <ActiveLink to="/signup">
      <Button id="signup-btn" variant="contained">
        Create Account &nbsp; {SIGNUP_ICON}
      </Button>
    </ActiveLink>
  );

  /** The login page output */
  return (
    <div className="login-page">
      <article id="login-article">
        <header className="login-title">
          <h1>Login</h1>
        </header>

        <section id="login-section">
          <ul className="fields-input">
            <li> {USERNAME_INPUT} </li>
            <li> {PASSWORD_INPUT} </li>
            {errorMessage}
          </ul>
          {LOGIN_BUTTON}
        </section>

        <section id="signup-section">
          <h2>Don't have an account?</h2>
          {SIGNUP_BUTTON}
        </section>
      </article>
    </div>
  );
}
