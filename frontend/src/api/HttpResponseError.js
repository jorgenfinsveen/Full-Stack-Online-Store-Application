/**
 * An exception to be used for HTTP error responses
 */
export class HttpResponseError extends Error {
  /**
   * Create a new HTTP response error. The reason of using
   * @code{super(message)} is because in the @class HttpResponseError
   * extends to @class {Error} and the class itslef has a constructor
   * istelf that expects a message as an argument.
   *
   * @param {int} statusCode The Status code: 200 for OK, 404 for Not found, etc.
   * @param {string} message The message in the response body
   */
  constructor(statusCode, message) {
    super(message);
    this.statusCode = statusCode;
  }

  /**
   * @return {int} The HTTP error code (401 unauthorized, etc)
   */
  getErrorCode() {
    return this.statusCode;
  }
}
