import { SmallDescription } from "../../components/text/SmallDescription";
import { TotalFee } from "../../components/text/TotalFee";
import { FormButton } from "../../components/buttons/FormButton";
import { RightButtonLayout } from "../../components/layout/RightButtonLayout";
import "./CartSection.css"

/**
 * Section for showing shopping cart items and addional fees, as well as the total fee.
 * The props.children is used for adding items to the cart.
 * The props.priceDetails is used for adding a descriptive text about additional fees.
 * The props.totalFee is used for displaying the total fee of the cart.
 * 
 * @param {*} props all the properties of the cart
 * @returns {JSX.Element} section for shopping cart and price details
 */
export function CartSectionFee(props) {
    return (
        <section className="cartSection" id="cartSectionFee">
            <SmallDescription text={props.priceDetails}></SmallDescription>
            <TotalFee text={props.totalFee}></TotalFee>
            <RightButtonLayout>
                <FormButton text="Submit" id="smallScreenProceedCheckoutBtn"></FormButton>
            </RightButtonLayout>
        </section>
    );
}