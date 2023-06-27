import { BigPriceSpan } from "../../components/text/BigPriceSpan";
import { ProductDisplayDescription } from "../../components/text/ProductDisplayDescription";
import { ProductDisplayTitle } from "../../components/text/ProductDisplayTitle";
import "./ProductInfoModule.css";

/**
 * Component consisting of product name, description and price.
 * 
 * @param {*} product the product to display
 */
export function ProductInfoModule({ product }) {
    return (
        <div className="productInfoModule">
            <ProductDisplayTitle text={product.name} />
            <ProductDisplayDescription text={product.description} />
            <BigPriceSpan text={product.price} />
        </div>
    );
}