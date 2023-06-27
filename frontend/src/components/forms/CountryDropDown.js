import { FormDropDown } from "../input/FormDropDown";

/**
 * A dropdown menu for selecting country.
 * 
 * @returns {JSX.Element} dropdown menu
 */
export function CountryDropDown() {
    const name = "country"
    return (
        <FormDropDown name={name}>
            <option value="norway">Norway</option>
            <option value="sweden">Sweden</option>
        </FormDropDown>
    );
}