import "./FormDropDown.css"

/**
 * A dropdown menu for forms.
 * 
 * @param {*} props option for the dropdown
 * @returns {JSX.Element} the dropdown menu with its children
 */
export function FormDropDown(props) {
    return (
        <select name="props.name" id="props.id" class="formDropDown">{props.children}</select>
    );
}