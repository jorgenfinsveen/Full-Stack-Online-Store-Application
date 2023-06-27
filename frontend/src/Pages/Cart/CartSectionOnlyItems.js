import { CartItemsWrapper } from "../../components/layout/CartItemsWrapper";
import { CartEngine } from "./CartEngine";
import { CartProvider } from "../../context/CartContext";
import "./CartSection.css"

/**
 * Section for showing shopping cart items and addional fees, as well as the total fee.
 * The props.children is used for adding items to the cart.
 * The props.priceDetails is used for adding a descriptive text about additional fees.
 * The props.totalFee is used for displaying the total fee of the cart.
 * 
 * @param {*} props all the properties of the cart
 * @returns {JSX.Element} section for shopping cart and price details
 */
export function CartSectionOnlyItems(props) {
    return (
        <section className="cartSection" id="cartSectionOnlyItems">
            <CartItemsWrapper>
                <CartEngine></CartEngine>
            </CartItemsWrapper>
        </section>
    );
}