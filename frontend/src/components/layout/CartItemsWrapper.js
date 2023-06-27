import "./CartItemsWrapper.css"

/**
 * Layout container for cart items.
 * 
 * @param {*} props the children of the container
 * @returns {JSX.Element} layout container and its children
 */
export function CartItemsWrapper(props) {
    return (
        <div className="cartItemsWrapper">{props.children}</div>
    );
}