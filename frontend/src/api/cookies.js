// Handling cookies

import { ExpandRounded } from "@mui/icons-material";

/**
 * Get value of a specific cookie.
 *
 * @param cname Cookie name (key)
 * @returns {string} Value of the cookie or "" if cookie not found
 */
export function getCookie(cname) {
  let name = cname + "=";
  // decodeURIComponent is a global method used to decode
  // a Uniform Resource Identifier (URI) component. It works
  // by taking a string of characters encoded as a URI component
  // decoding it into its original form.
  let decodedCookie = decodeURIComponent(document.cookie);
  let ca = decodedCookie.split(";");
  for (let i = 0; i < ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) === " ") {
      c = c.substring(1);
    }
    if (c.indexOf(name) === 0) {
      return c.substring(name.length, c.length);
    }
  }

  return "";
}

/**
 * Store a local cookie. The parameters of the function
 * below are the name of the cookie (cname), the value of
 * the cookie (cvalue), and the number of days until the
 * cookie should expire (exdays). The function sets a cookie
 * by adding together the cookiename, the cookie value, and
 * the expires string.
 *
 * @param cname Name of the cookie (Key)
 * @param cvalue Value of the cookie
 * @param exdays Expiry time in days
 */
export function setCookie(cname, cvalue, exdays) {
  const d = new Date();
  d.setTime(d.getTime() + exdays * 24 * 60 * 60 * 1000);
  let expires = "expires=" + d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

/**
 * Delete a cookie.
 *
 * @param cookieName Name of the cookie to delete
 */
export function deleteCookie(cookieName) {
  setCookie(cookieName, "", -1);
}
