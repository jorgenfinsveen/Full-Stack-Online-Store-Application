import { FormWrapper } from "../layout/FormWrapper";
import { FormHeader } from "../text/FormHeader";
import { FormTextInput } from "../input/FormTextInput";

/**
 * A form containing text fields for email and phone number.
 * 
 * @returns {JSX.Element} a formwrapper with its elements
 */
export function ContactInformationForm() {
    const formHeader = "Contact information"
    const placeholder1 = "Email"
    const placeholder2 = "Phone number"
    return (
        <FormWrapper>
            <FormHeader text={formHeader}></FormHeader>
            {/*<FormTextInput placeholder={placeholder1}></FormTextInput>*/}
            <FormTextInput placeholder={placeholder2}></FormTextInput>
        </FormWrapper>
    );
}