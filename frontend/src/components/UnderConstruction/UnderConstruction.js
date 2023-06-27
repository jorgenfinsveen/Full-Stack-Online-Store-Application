import "./UnderConstruction.css";

/**
 * A component that communicates that a section is under construction
 *
 * @return {JSX.Element}
 * @constructor
 */
export function UnderConstruction() {
  return (
    <section className="under-construction-section">
      <article className="info-box">
        <h2>Page under construction!</h2>
        <i class="fa fa-cogs" aria-hidden="true"></i>
      </article>
    </section>
  );
}
