import { useState } from "react";
import { ActiveLink } from "../../components/Navigation/ActiveLink";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUser } from "@fortawesome/free-solid-svg-icons";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import "./Signup.css";
import { useNavigate } from "react-router-dom";
import { HttpInterface, SESSION } from "../../api/HttpInterface";

/**
 * Sign Up form component.
 *
 * @component
 * @param {Object} props - Component props
 * @returns {JSX.Element}
 */
export function Signup(props) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  let errorMessage = null;
  if (error) {
    errorMessage = <p className="error">{error}</p>;
  }

  const [strengthIndicator, setStrengthIndicator] = useState("");

  /**
   * Hnadle the change event for the password input.
   *
   * @param {Object} event - The change event.
   */
  const handlePasswordChange = (event) => {
    const password = event.target.value;
    setPassword(password);
    setStrengthIndicator(determineStrength(password));
  };

  /**
   * Username inputs for handling
   * the textfield with different
   * parameters.
   */
  const USERNAME_INPUT = (
    <TextField
      variant="filled"
      label="Username"
      defaultValue=""
      value={username}
      onChange={(event) => setUsername(event.target.value)}
    />
  );

  /**
   * Username inputs for handling
   * the textfield with different
   * parameters.
   */
  const EMAIL_INPUT = (
    <TextField
      variant="filled"
      label="Email"
      defaultValue=""
      value={email}
      onChange={(event) => setEmail(event.target.value)}
    />
  );

  /**
   * Password inputs for handling
   * the textfield with different
   * parameters.
   */
  const PASSWORD_INPUT = (
    <TextField
      variant="filled"
      label="Password"
      id="password"
      type="password"
      defaultValue=""
      value={password}
      onChange={handlePasswordChange}
    />
  );

  /**
   * Sign up button with the given
   * sign up icon.
   */
  const SIGNUP_BUTTON = (
    <Button id="signup-btn-pagesignup" variant="contained" onClick={submitForm}>
      Sign Up &nbsp; {SIGNUP_ICON}
    </Button>
  );

  /** The strenght container */
  const STRENGTH_CONTAINER = (
    <>
      <div className={`strength-bar ${strengthIndicator}`}>
        <div></div>
      </div>
      <div id="strength-label">
        {strengthIndicator && <>{strengthIndicator} password</>}
      </div>
    </>
  );

  /** Returns the sign up page with all the components */
  return (
    <div className="signup-page">
      <main id="signup">
        <article id="signup-article">
          <header className="signup-title">
            <h1>Registration</h1>
          </header>

          <form className="signup-section">
            <div className="username-field">{USERNAME_INPUT}</div>
            <div className="email-field">{EMAIL_INPUT}</div>
            <div className="password-field">{PASSWORD_INPUT}</div>
            <div className="strength-indicator">{STRENGTH_CONTAINER}</div>
          </form>

          <section className="signup-buttons">
            <div id="login-btn-container">{LOGIN_BUTTON}</div>
            <div id="signup-btn-container">{SIGNUP_BUTTON}</div>
            {errorMessage}
          </section>
        </article>
      </main>
    </div>
  );

  /**
   * Submit the sign-up form
   * @param event
   */
  async function submitForm(event) {
    event.preventDefault();
    const signupData = {
      username: username,
      password: password,
      email: email,
    };
    await HttpInterface.signUp(signupData);

    if (SESSION.Authorized) {
      navigate("/");
    } else {
      setUsername("");
      setPassword("");
      setEmail("");
    }
  }
}

/** Strenght indicator for the sign in, indicating
 * the different indicators for the clients password.
 */
const strengthIndicators = ["weak", "decent", "strong"];

/** Login Button for the people already having an acount,
 * and returning back to the login site.
 */
const LOGIN_BUTTON = (
  <ActiveLink to="/login">
    <Button id="login-btn-pagesignup" variant="outlined">
      Already have an account?
    </Button>
  </ActiveLink>
);

/** Sign Up Icon for the Sign Up button */
const SIGNUP_ICON = (
  <FontAwesomeIcon icon={faUser} style={{ color: "#ffffff" }} />
);

/**
 * Strength indicator for the sign up for the
 * password. Checking the password for
 * uppercase, lowercase and numerical.
 */
function determineStrength(password) {
  let indicator = -1;
  let upperCase = false;
  let lowerCase = false;
  let nummerical = false;

  for (let index = 0; index < password.length; index++) {
    const char = password.charCodeAt(index);

    if (!upperCase && char >= 65 && char <= 90) {
      upperCase = true;
      indicator++;
    }

    if (!lowerCase && char >= 97 && char <= 122) {
      lowerCase = true;
      indicator++;
    }

    if (!nummerical && char >= 48 && char <= 57) {
      nummerical = true;
      indicator++;
    }
  }

  if (password.length < 10) indicator = 1;
  if (password.length < 5) indicator = 0;
  if (password.length < 1) indicator = -1;

  return strengthIndicators[indicator];
}
