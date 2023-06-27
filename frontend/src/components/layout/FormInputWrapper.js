import "./FormInputWrapper.css";

/**
 * A horizontal wrapper for form elements.
 * 
 * @param {*} props any children of the wrapper
 * @returns {JSX.Element} the wrapper with its children
 */
export function FormInputWrapper(props) {
    return (
        <div className="formInputWrapper">{props.children}</div>
    );
}