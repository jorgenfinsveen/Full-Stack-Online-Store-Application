import { Rating } from "./Rating";
import "./TestimonialCard.css";

/**
 * Creates a TestimonialCard component
 * @constructor
 * @returns {JSX.Element}
 */
export function TestimonialCard({ testimonialInfo }) {
  return (
    <div className="testimonial-card">
      <Rating />
      <p>{testimonialInfo.comment}</p>
      <img
        src={require("../../assets/img/" + testimonialInfo.name + ".jpg")}
        alt="A customer"
        className="testimonial-portrait"
      />
      <h3>{testimonialInfo.name}</h3>
    </div>
  );
}
