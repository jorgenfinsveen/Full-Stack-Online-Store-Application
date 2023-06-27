import "./FormWrapper.css"
/**
 * A vertical wrapper for form elements.
 * 
 * @param {*} props children of the form
 * @returns {JSX.Element} the wrapper and its children
 */
export function FormWrapper(props) {
    return (
        <div className="formWrapper">{props.children}</div>
    );
}