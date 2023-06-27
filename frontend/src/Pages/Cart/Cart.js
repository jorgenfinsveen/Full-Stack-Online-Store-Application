import React, { useContext } from "react";
import { CartSection } from './CartSection';
import { CartForm } from '../../components/forms/CartForm';
import { CartSectionOnlyItems } from "./CartSectionOnlyItems";
import { CartSectionFee } from "./CartSectionFee";
import { CartContext } from "../../context/CartContext";
import './Cart.css';


/**
 * A react component responsible for displaying the shopping cart page to the user.
 * 
 * @returns {JSX.Element} div element with its children
 */
export function Cart() {
    const { totalPrice } = useContext(CartContext);

    // total fee description string
    const feeDescription = "Extra fees might be added to the total due to differences in the shipping locations. The total"
        + "fee will be calculated at the next step."

    // string for the total fee of the cart
    const totalFee = "Total: NOK " + totalPrice + ",-";

    return (
        <div className="cartPage">
            <CartSectionOnlyItems>
            </CartSectionOnlyItems>
            <CartForm></CartForm>
            <CartSection priceDetails={feeDescription} totalFee={totalFee}>
            </CartSection>
            <CartSectionFee priceDetails={feeDescription} totalFee={totalFee}></CartSectionFee>
        </div>
    );
}