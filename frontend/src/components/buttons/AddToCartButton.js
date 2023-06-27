import { useContext } from "react";
import "./AddToCartButton.css"
import { CartContext } from "../../context/CartContext";

/**
 * A button for adding items to the cart.
 * 
 * @param {*} props text to be displayed on button
 * @returns {JSX.Element} a button with the desired text
 */
export function AddToCartButton({product}) {
    const {addToCart} = useContext(CartContext);

    // Adds a random item to the cart
    const addItemToCart = () => {
        addToCart(product);
    };

    return (
        <button className="addToCartButton" onClick={addItemToCart}>Add to cart</button>
    );
}