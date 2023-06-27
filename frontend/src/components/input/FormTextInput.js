import "./FormTextInput.css"

/**
 * Text input for forms.
 * 
 * @param {*} props placeholder
 * @returns {JSX.Element} text input with desired placeholder
 */
export function FormTextInput(props) {
    return (
        <input type="text" placeholder={props.placeholder} className="formTextInput"></input>
    );
}