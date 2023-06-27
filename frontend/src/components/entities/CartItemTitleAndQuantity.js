import { ProductQuantityInput } from "../input/ProductQuantityInput";
import { SmallProductTitle } from "../text/SmallProductTitle";
import "./CartItemTitleAndQuantity.css"
import { FlexDummy } from "./FlexDummy";

/**
 * A container containing the title and quantity of a product in a shopping cart.
 * 
 * @param {*} props the title and quantity of product
 * @returns {JSX.Element} div with product title and quantity
 */
export function CartItemTitleAndQuantity(props) {
    return (
        <div className="cartItemTitleAndQuantity">
            <SmallProductTitle text={props.text}></SmallProductTitle>
            <FlexDummy></FlexDummy>
            <ProductQuantityInput value={props.value}></ProductQuantityInput>
        </div>
    );
}