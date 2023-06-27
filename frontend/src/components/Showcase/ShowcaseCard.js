import "./ShowcaseCard.css";
import * as React from "react";
import { Link } from "react-router-dom";

/**
 * A showcase of a product
 *
 * @param props the properties of the product being showcased
 * @return {JSX.Element}
 * @constructor
 */
export function ShowcaseCard({ props }) {
  return (
    <Link to={`/productdetails/${props.id}`} className="productCardLink">
      <article className="card-container">
        <img
          className="product-image"
          src={`data:image/png;base64,${props.image.imageData}`}
          alt={props.image.alt}
        />
        <div className="card-overlay"></div>
        <div className="card-info">
          <div className="card-stats">
            <h3>{props.name}</h3>
          </div>
          <p>{props.description}</p>
          <h4>{props.price}kr</h4>
        </div>
      </article>
    </Link>
  );
}
