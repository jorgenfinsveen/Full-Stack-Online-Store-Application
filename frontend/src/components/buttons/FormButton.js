import { useContext } from "react";
import "./FormButton.css"
import { CartContext } from "../../context/CartContext";
import { HttpInterface } from "../../api/HttpInterface";
import { useNavigate } from "react-router-dom";

/**
 * A button for forms.
 * 
 * @param {*} props text to be displayed on button
 * @returns {JSX.Element} a button with the desired text
 */
export function FormButton(props) {
    const { cartItems } = useContext(CartContext);
    const navigate = useNavigate();

    const submit = async () => {

        if (sessionStorage.getItem("UID") === null) { 
            navigate("/login");
        } else {
            const submitList = cartItems.map((item) => {
                return {
                    productId: item.id,
                    amount: item.quantity,
                };
            });
            console.log(submitList);
            HttpInterface.submitOrder(sessionStorage.getItem("UID"), submitList);
            navigate("/");
        } 
    }

    return (
        <button className="formButton"
            id={props.id}
            onClick={submit}
        >{props.text}</button>
    );
}