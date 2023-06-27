import "./FormHeader.css"

/**
 * A header for forms.
 * 
 * @param {*} props the text displayed
 * @returns a header with the desired text
 */
export function FormHeader(props) {
    return (
        <h2 className="formHeader">{props.text}</h2>
    );
}