import "./Testimonials.css";
import { TestimonialCard } from "./TestimonialCard";
import testimonails from "../../assets/testimonials.json";

/**
 * Creates a Carousel componentnpm
 * @constructor
 * @returns {JSX.Element}
 */
export function Testimonials() {
  return (
    <section className="testimonial-section">
      <h2>What our customers say</h2>
      <article className="testimonial-container">
        <TestimonialCard testimonialInfo={testimonails[0]} />
        <TestimonialCard testimonialInfo={testimonails[3]} />
        <TestimonialCard testimonialInfo={testimonails[2]} />
      </article>
    </section>
  );
}
