import "./ProductDisplayTitle.css"

/**
 * Title for product displays.
 * 
 * @param {*} props text to be displayed
 * @returns {JSX.Element} h2 element with desired text
 */
export function ProductDisplayTitle(props) {
    return (
        <h2 className="productDisplayTitle">{props.text}</h2>
    );
}