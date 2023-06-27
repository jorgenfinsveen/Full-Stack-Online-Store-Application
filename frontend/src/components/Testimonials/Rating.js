import "./Rating.css";

/**
 * Creates a Rating component
 * @constructor
 * @returns {JSX.Element}
 */
export function Rating() {
  return (
    <article className="rating">
      <i className="fa fa-star fa-5x" aria-hidden="true"></i>
      <i className="fa fa-star fa-5x" aria-hidden="true"></i>
      <i className="fa fa-star fa-5x" aria-hidden="true"></i>
      <i className="fa fa-star fa-5x" aria-hidden="true"></i>
      <i className="fa fa-star fa-5x" aria-hidden="true"></i>
    </article>
  );
}
