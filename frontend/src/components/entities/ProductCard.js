import { Link } from "react-router-dom";
import { ProductCardImage } from "../img/ProductCardImage";
import { AddToCartButton } from "../buttons/AddToCartButton"
import "./ProductCard.css";
import { ProductCardTitle } from "../text/ProductCardTitle";

/**
 * Component representing a product card.
 * 
 * @param {*} props the properties of the product card
 * @returns {JSX.Element} div element
 */
export function ProductCard({ product }) {
    return (
        <div className="productCardContainer">
            <div className="productCard">
                <Link to={`/productdetails/${product.id}`} className="productCardLink">
                    <ProductCardImage src={`data:image/png;base64,${product.image.imageData}`} alt={product.image.alt}></ProductCardImage>
                    <ProductCardTitle text={product.name} />
                </Link>
                <AddToCartButton product={product}></AddToCartButton>
            </div>
        </div>
    );
};