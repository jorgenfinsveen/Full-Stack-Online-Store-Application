import "./ProductCardImage.css"

/**
 * An image for product cards.
 * 
 * @param {*} props the src and alt of the image
 * @returns {JSX.Element} an img element
 */
export function ProductCardImage(props) {
    return (
        <img src={props.src} className="productCardImage" alt={props.alt}></img>
    );
}