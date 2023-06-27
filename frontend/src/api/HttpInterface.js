// All the code for sending requests to backend is stored in the HttpInterface file

// Imports
import { createAsyncThunk } from "@reduxjs/toolkit";
import { HttpResponseError } from "./HttpResponseError";
import { getCookie } from "./cookies";
import axios from "axios";
axios.defaults.headers.post["Content-Type"] = "application/json";

// Import the REST API BASE URL from the environment variable
// const SERVER_URL = "process.env.REACT_APP_BASE_URL";
const SERVER_URL = "https://group10.web-tek.ninja:8080";

/**
 * Send and asynchronous request to the remote API.
 *
 * @param {string} method the HTTP method to use: GET, POST, PUT. Case-insensitive.
 * @param {string} url The relative API url (bae URL is added automatically)
 * @param {object} requestBody The data to send in request body. Ignored for HTTP GET.
 * @param {boolean} returnPlainText When true, return plain text instead of JSON object.
 * @returns @return {Promise<JSON>} The response body, parsed as a JSON
 * @throws {HttpResponseError} Error code and message from the response body
 */
export function asyncApiRequest(
  method,
  url,
  requestBody = null,
  returnPlainText = false
) {
  const fullUrl = SERVER_URL + url;
  let body = null;
  let headers = {};
  if (method.toLowerCase() !== "get" && requestBody) {
    headers["Content-Type"] = "application/json";
    body = JSON.stringify(requestBody);
  }
  const jwtToken = getCookie("jwt");
  if (jwtToken) {
    headers["Authorization"] = "Bearer " + jwtToken;
  }

  return fetch(fullUrl, {
    method: method,
    mode: "cors",
    headers: headers,
    body: body,
  })
    .then(handleErrors)
    .then((response) => (returnPlainText ? response : response.json()));
}

/**
 * Check whether the HTTP response has a 200 OK status. If it does, return the
 * response. If it does not, throw an Error.
 *
 * @param response
 * @return {response} The response (if all was OK)
 * @throws Error containing the response code and text from the response body
 */
async function handleErrors(response) {
  if (!response.ok) {
    const responseText = await response.text();
    throw new HttpResponseError(response.status, responseText);
  }
  return response;
}

export const SESSION = {
  Token: "",
  Authorized: false,
};

async function httpGET(endpoint) {
  const response = await fetch(SERVER_URL + endpoint, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + SESSION.Token,
    },
  });
  return response.json();
}

async function httpGETtext(endpoint) {
  const response = await fetch(SERVER_URL + endpoint, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + SESSION.Token,
    },
  });
  return response.text();
}


async function httpSendCredentials(endpoint, method, data) {
  return await fetch(SERVER_URL + endpoint, {
    method: method,
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(data),
  });
}

async function httpSendData(endpoint, method, data) {
  return await fetch(SERVER_URL + endpoint, {
    method: method,
    headers: {
      "Content-Type": "application/json",
      Authorization: sessionStorage.getItem("JWT"),
    },
    body: JSON.stringify(data),
  });
}



export const HttpInterface = {

  getUserId: async function (username) {
    const response = await httpGETtext("/users/" + username);
    sessionStorage.setItem("UID", response);
  },

  authenticateLogin: async function (credentials) {
    const response = await httpSendCredentials("/login", "POST", credentials);
    if (response.ok) {
      const authHeader = response.headers.get("Authorization").split(" ");
      SESSION.Token = authHeader[1];
      sessionStorage.setItem("JWT", authHeader[1]);
      sessionStorage.setItem("USERNAME", credentials.username);
      SESSION.Authorized = true;
      this.getUserId(credentials.username);
    } else {
      window.alert("Bad credentials. Please try again");
    }
    return SESSION.Authorized;
  },

  signUp: async function (credentials) {
    const response = await httpSendCredentials("/users", "POST", credentials);
    if (response.ok) {
      const authHeader = response.headers.get("Authorization").split(" ");
      SESSION.Token = authHeader[1];
      sessionStorage.setItem("JWT", authHeader[1]);
      sessionStorage.setItem("USERNAME", credentials.username);
      SESSION.Authorized = true;
      this.getUserId(credentials.username);
    }
    return SESSION.Authorized;
  },

  getAllProducts: async function () {
    const response = await httpGET("/products");
    return response.ok ? response.json() : null;
  },

  getUserDetails: async function (userId) {
    const response = await httpGET("/users/" + userId);
    return response.ok ? response.json() : null;
  },


  submitOrder: async function (userId, listOfCartItems) {
    const response = await httpSendData("/orders/user/" + userId, "POST", listOfCartItems);
  },
};
