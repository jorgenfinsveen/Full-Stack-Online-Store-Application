import { useState, useEffect, useContext } from "react";
import "./Navbar.css";
import { ActiveLink } from "./ActiveLink";
import { CartContext } from "../../context/CartContext";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";

/**
 * A collection of links to navigate the webpage.
 *
 * @return {JSX.Element} navbar element
 * @constructor
 */
export function NavBar() {
  const [itemsInCart, setNumberOfItemsInCart] = useState(0);
  const { cartItems } = useContext(CartContext);
  const cartSize = cartItems.length;
  const navigate = useNavigate();

  useEffect(loadItemsInCart);

  // Check if the user is logged ins

  return (
    <nav className="navbar-container">
      <article class="dropdown">
        <i className="fa fa-bars fa-5x" aria-hidden="true" id="bars-icon"></i>
        <div class="dropdown-content">
          <ActiveLink to="/">
            <h4>HOME</h4>
          </ActiveLink>
          <ActiveLink to="/products">
            <h4>PRODUCTS</h4>
          </ActiveLink>
          <ActiveLink to="/about">
            <h4>ABOUT US</h4>
          </ActiveLink>
          <ActiveLink to="/contact">
            <h4>CONTACT</h4>
          </ActiveLink>
        </div>
      </article>
      <Link to={`/`}>
        <i className="fa fa-coffee fa-5x" aria-hidden="true" id="logo"></i>
      </Link>
      <ActiveLink to="/">
        <h4>HOME</h4>
      </ActiveLink>
      <ActiveLink to="/products">
        <h4>PRODUCTS</h4>
      </ActiveLink>
      <ActiveLink to="/about">
        <h4>ABOUT US</h4>
      </ActiveLink>
      <ActiveLink to="/contact">
        <h4>CONTACT</h4>
      </ActiveLink>
      <div className="user-cart-container">
        <ActiveLink to="/login">
          <i className="fa fa-user fa-5x" aria-hidden="true" id="user-icon"></i>
        </ActiveLink>
        <div className="cart-container">
          <ActiveLink to="/cart">
            <i
              className="fa fa-shopping-cart fa-5x"
              aria-hidden="true"
              id="cart-icon"
            ></i>
          </ActiveLink>
          <div className="item-counter">{itemsInCart}</div>
        </div>
      </div>
    </nav>
  );

  /**
   *
   */
  function loadItemsInCart() {
    // Send request to API for the number of items in the users cart
    // E.g queryCartItems().length

    // Dummy number to showcase functionality
    const numberOfItems = cartSize;

    setNumberOfItemsInCart(numberOfItems);
  }
}
