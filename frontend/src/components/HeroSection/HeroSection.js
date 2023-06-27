import { Link } from "react-router-dom";
import "./HeroSection.css";

/**
 * Component representing the Hero section of the homepage
 * @return {JSX.Element}
 * @constructor
 */
export function HeroSection() {
  return (
    <section className="hero-section-container">
      <article className="hero-section-content">
        <h1>Try our new exciting flavors</h1>
        <Link to={`/products`}>
          <button className="hero-section-button">Find out more</button>
        </Link>
      </article>
    </section>
  );
}
