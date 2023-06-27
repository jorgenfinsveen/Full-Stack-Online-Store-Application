import React, { createContext, useState, useEffect } from "react";

// creates a new context
export const CartContext = createContext();

/**
 * A context for manipulating the shopping cart of a user.
 * 
 * @param children the children of the context
 * @returns the cart context with its children
 */
export const CartProvider = (props) => {
    const [cartItems, setCartItems] = useState([]);
    const [totalPrice, setTotalPrice] = useState(0);

    /**
     * Adds an item to the cart.
     * 
     * @param {Object} item item to add
     */
    const addToCart = (item) => {
        const existingItem = cartItems.find((i) => i.id === item.id);
        if (existingItem) {
            setCartItems((prevItems) =>
                prevItems.map((i) =>
                    i.id === item.id ? { ...i, quantity: i.quantity + 1 } : i
                )
            );
        } else {
            setCartItems((prevItems) => [...prevItems, { ...item, quantity: 1 }]);
        }
    };

    /**
     * Removes an item from the cart.
     * 
     * @param {Object} item item to remove
     */
    const removeFromCart = (item) => {
        setCartItems((prevItems) => prevItems.filter((i) => i.id !== item.id));
    };

    /**
     * Clears the cart of all items.
     */
    const clearCart = () => {
        setCartItems([]);
    };

    /**
     * Updates the quantity of a spesific item in the cart.
     * 
     * @param {*} item the item to update 
     * @param {*} newQuantity the new quantity
     */
    const updateQuantity = (item, newQuantity) => {
        setCartItems((prevItems) =>
            prevItems.map((i) =>
                i.id === item.id ? { ...i, quantity: newQuantity } : i
            )
        );
    };


    // runs on every useeffect
    useEffect(() => {
        /**
         * Updates the total price of all items currently in the cart.
         */
        const total = cartItems.reduce((accumulator, item) => {
            return accumulator + item.price * item.quantity;
        }, 0);
        setTotalPrice(total);
    }, [cartItems]);

    return (
        <CartContext.Provider value={{ cartItems, addToCart, removeFromCart, clearCart, totalPrice, updateQuantity }}>
            {props.children}
        </CartContext.Provider>
    );
};