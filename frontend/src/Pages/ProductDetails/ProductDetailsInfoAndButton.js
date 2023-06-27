import { AddToCartButton } from "../../components/buttons/AddToCartButton";
import "./ProductDetailsInfoAndButton.css";
import { ProductInfoModule } from "./ProductInfoModule";

/**
 * Component containing product information as well as a add to cart button.
 * 
 * @param {*} product the product to display 
 */
export function ProductDetailsInfoAndButton({ product }) {
    return (
        <div className="productDetailsInfoAndButton">
            <ProductInfoModule product={product} />
            <AddToCartButton product={product}/>
        </div>
    );
}