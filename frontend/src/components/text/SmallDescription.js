import "./SmallDescription.css"

/**
 * Component for displaying a small descriptive text.
 * 
 * @param {*} props text to display
 * @returns {JSX.Element} a paragraph element
 */
export function SmallDescription(props) {
    return (
        <p className="smallDescription">{props.text}</p>
    );
}