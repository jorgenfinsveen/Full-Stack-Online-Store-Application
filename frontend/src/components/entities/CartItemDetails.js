import { SmallProductImage } from "../img/SmallProductImage";
import { SmallProductTitle } from "../text/SmallProductTitle"
import { ProductQuantityInput } from "../input/ProductQuantityInput"
import "./CartItemDetails.css"

/**
 * A container with cart product image, title and quantity.
 * 
 * @param {*} props image src, title text and quantity initial value
 * @returns {JSX.Element} div with children
 */
export function CartItemDetails(props) {
    return (
        <div className="cartItemDetails">
            <SmallProductImage src={props.src} alt={props.alt}></SmallProductImage>
            <SmallProductTitle text={props.text}></SmallProductTitle>
            <ProductQuantityInput item={props.item}></ProductQuantityInput>
        </div>
    );
}