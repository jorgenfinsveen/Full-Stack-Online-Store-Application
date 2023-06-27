import "./BigProductImage.css"

/**
 * A big image for product displays.
 * 
 * @param {*} props the src and alt of the image
 * @returns {JSX.Element} an img element
 */
export function BigProductImage(props) {
    return (
        <img src={props.src} className="bigProductImage" alt={props.alt}></img>
    );
}