import React, { useContext } from "react";
import "./ProductQuantityInput.css";

/**
 * Input field for the quantity for a spesific product.
 * 
 * @param {*} props - The item object containing the quantity.
 * @returns {JSX.Element} - Number input component.
 */
export function ProductQuantityInput(props) {
    return (
        <input
            type="number"
            name="quantity"
            min="1"
            className="productQuantityInput"
            value={props.item.quantity}
        />
    );
}