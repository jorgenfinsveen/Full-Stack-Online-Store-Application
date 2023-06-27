import React, { useContext } from "react";
import { CartContext } from "../../context/CartContext";
import "./RemoveCartProductButton.css"
/**
 * A button that removes a product from a shopping cart.
 * 
 * @returns {JSX.Element} button removing cart product
 */
export function RemoveCartProductButton(props) {
    const { removeFromCart } = useContext(CartContext);

    const handleRemove = () => {
        removeFromCart(props.item);
    };

    return (
        <button className="removeCartProductButton" onClick={handleRemove}>
            X
        </button>
    );
}