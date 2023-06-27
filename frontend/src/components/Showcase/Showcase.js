import "./Showcase.css";
import { ShowcaseCard } from "./ShowcaseCard";
import React, { useEffect, useState } from "react";
import axios from "axios";

/**
 * A section of showcased products
 *
 * @return {JSX.Element}
 * @constructor
 */
export function Showcase() {
  const [products, setProducts] = useState([]);

  // fetches data once the component is mounted
  useEffect(() => {
    fetchData();
  }, []);

  // fetches product data using swagger api
  const fetchData = async () => {
    try {
      const response = await axios.get(
        "https://group10.web-tek.ninja:8080/products/order?order=popularity"
      );
      setProducts(response.data);
      console.log(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <section className="product-section">
      <h2>Popular Releases</h2>
      <article className="product-container">
        {products.map((product) => (
          <ShowcaseCard key={product.id} props={product} />
        ))}
      </article>
    </section>
  );
}
