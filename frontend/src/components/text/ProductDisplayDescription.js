import "./ProductDisplayDescription.css"

/**
 * Component displaying the description of a product in a product display.
 * 
 * @param {*} props the text to display
 * @returns {JSX.Element} p element with desired text
 */
export function ProductDisplayDescription(props) {
    return (
        <p className="productDisplayDescription">{props.text}</p>
    );
}