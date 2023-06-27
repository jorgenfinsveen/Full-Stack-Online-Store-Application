// User Authentication and authorization

// Imports
import { deleteCookie, getCookie, setCookie } from "./cookies";
import { asyncApiRequest } from "./HttpInterface";

/**
 * Retrieves the currently authenticated user. It checks for the
 * presence of cookie named "current_username" and "current_user_roles".
 * If both cookies exist, it creates and returns a user object with the
 * username and roles extracted from the cookies. Otherwise, it return
 * @code {null}.
 *
 * @returns User object or null if user is not authenticated.
 */
export function getAuthenticatedUser() {
  let user = null;
  const username = getCookie("current_username");
  const commaSeparateRoles = getCookie("current_user_roles");
  if (username && commaSeparateRoles) {
    const roles = commaSeparateRoles.split(",");
    user = {
      username: username,
      roles: roles,
    };
  }
  return user;
}

/**
 * Checks if the given user has admin rights. It expects
 * a user object as a parameter and checks if the user exists,
 * has the role property.
 *
 * @param user
 * @returns {boolean}
 */
export function isAdmin(user) {
  return user && user.roles && user.roles.includes("ROLE_ADMIN");
}

/**
 * Send authentication request to the API.
 *
 * @param username Username
 * @param password Password, plain text
 * @param successCallback Function to call on success
 * @param errorCallback Function to call on error, with response text as the parameter
 */
export async function sendAuthenticationRequest(
  username,
  password,
  successCallback,
  errorCallback
) {
  const postData = {
    username: username,
    password: password,
  };
  try {
    const jwtResponse = await asyncApiRequest("POST", "/login", postData, false);
    console.log(jwtResponse.headers.get("Authorization"));
    if (true) {
      setCookie("jwt", jwtResponse.headers.get("Authorization"));
      const userData = parseJwtUser(jwtResponse.headers.get("Authorization"));
      if (userData) {
        setCookie("current_username", userData.username);
        setCookie("current_user_roles", userData.roles.join(","));
        successCallback(userData);
      }
    }
  } catch (httpError) {
    errorCallback(httpError.message);
  }
}

/**
 * Parse JWT string, extract information from it.
 *
 * @param token JWT token string
 * @returns {any} Decoded JWT object
 */
function parseJwt(token) {
  const base64Url = token.split(".")[1];
  const base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
  const jsonPayload = decodeURIComponent(
    atob(base64)
      .split("")
      .map(function (c) {
        return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
      })
      .join("")
  );

  return JSON.parse(jsonPayload);
}

/**
 * Parse JWT string, extract a User object.
 *
 * @param jwtString
 * @returns User object
 */
function parseJwtUser(jwtString) {
  let user = null;
  const jwtObject = parseJwt(jwtString);
  if (jwtObject) {
    user = {
      username: jwtObject.sub,
      roles: jwtObject.roles.map((r) => r.authority),
    };
  }
  return user;
}

/**
 * Delete all cookies related to authorization (user session)
 */
export function deleteAuthorizationCookies() {
  deleteCookie("jwt");
  deleteCookie("current_username");
  deleteCookie("current_user_roles");
}
