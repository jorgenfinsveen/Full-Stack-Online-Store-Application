import "./SmallProductImage.css"

/**
 * An image for small product entities.
 * 
 * @param {*} props the src and alt of the image
 * @returns {JSX.Element} a small image
 */
export function SmallProductImage(props) {
    return (
        <img src={props.src} className="smallProductImage" alt={props.alt}></img>
    );
}