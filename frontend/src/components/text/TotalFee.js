import "./TotalFee.css"

/**
 * Component for displaying the total fee of something.
 * 
 * @param {*} props the text to be displayed
 * @returns {JSX.Element} span element with desired text
 */
export function TotalFee(props) {
    return (
        <span className="totalFee">{props.text}</span>
    );
}