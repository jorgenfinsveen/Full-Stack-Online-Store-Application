import { FormInputWrapper } from "../layout/FormInputWrapper";
import { FormWrapper } from "../layout/FormWrapper";
import { FormHeader } from "../text/FormHeader";
import { FormTextInput } from "../input/FormTextInput";
import { CountryDropDown } from "./CountryDropDown";

export function ShippingAddressForm() {
    const formHeader = "Shipping address"
    const placeholder1 = "First name"
    const placeholder2 = "Last name"
    const placeholder3 = "Company (required for business addresses)"
    const placeholder4 = "Address"
    const placeholder5 = "City"
    const placeholder6 = "ZIP code"
    
    return (
        <FormWrapper>
            <FormHeader text={formHeader}></FormHeader>
            <FormInputWrapper>
                <FormTextInput placeholder={placeholder1}></FormTextInput>
                <FormTextInput placeholder={placeholder2}></FormTextInput>
            </FormInputWrapper>
            <FormTextInput placeholder={placeholder3}></FormTextInput>
            <FormTextInput placeholder={placeholder4}></FormTextInput>
            <FormInputWrapper>
                <CountryDropDown></CountryDropDown>
                <FormTextInput placeholder={placeholder5}></FormTextInput>
                <FormTextInput placeholder={placeholder6}></FormTextInput>
            </FormInputWrapper>
        </FormWrapper>
    );
}