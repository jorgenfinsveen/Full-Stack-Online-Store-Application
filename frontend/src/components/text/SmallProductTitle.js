import "./SmallProductTitle.css"

/**
 * A title for small product entities.
 * 
 * @param {*} props text displayed
 * @returns {JSX.Element} title of desired text
 */
export function SmallProductTitle(props) {
    return (
        <h3 className="smallProductTitle">{props.text}</h3>
    );
}