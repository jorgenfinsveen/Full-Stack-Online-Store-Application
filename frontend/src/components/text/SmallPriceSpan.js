import "./SmallPriceSpan.css"
/**
 * A price tag for small product entities.
 * 
 * @param {*} props 
 * @returns a small span with the desired price
 */
export function SmallPriceSpan(props) {
    return (
        <span className="smallPriceSpan">{props.text}</span>
    );
}