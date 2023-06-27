import "./ProductCardTitle.css";

/**
 * Componenet representing a title for product cards. 
 * 
 * @param {*} props properties of the title
 * @returns {JSX.Element} h2 element with desired title
 */
export function ProductCardTitle(props) {
    return (
        <h2 className="productCardTitle">
            {props.text}
        </h2>
    );
}