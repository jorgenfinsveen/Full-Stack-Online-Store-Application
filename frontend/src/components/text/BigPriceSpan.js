import "./BigPriceSpan.css";

/**
 * Component representing a big price display.
 * 
 * @param {*} props text to display
 * @returns {JSX.Element} span element with desired text
 */
export function BigPriceSpan(props) {
    return (
        <span className="bigPriceSpan">NOK {props.text},-</span>
    );
}