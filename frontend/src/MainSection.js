import { Route, Routes } from "react-router-dom";
import { Home } from "./Pages/Home/Home";
import { About } from "./Pages/About/About";
import { Contact } from "./Pages/Contact/Contact";
import { Cart } from "./Pages/Cart/Cart";
import { ProductPage } from "./Pages/ProductPage/ProductPage";
import "./MainSection.css";
import { Login } from "./Pages/Login/Login";
import { Signup } from "./Pages/Login/Signup";
import { AdminPage } from "./Pages/AdminPage/AdminPage";
import { ProfilePage } from "./Pages/ProfilePage/ProfilePage";
import { LogOut } from "./Pages/Login/LogOut";
import { ProductDetails } from "./Pages/ProductDetails/ProductDetails";

export function MainSection(props) {
  const username = props.user ? props.user.username : null;

  return (
    <main>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route
          path="/products"
          element={<ProductPage products={props.products} />}
        />
        <Route path="/admin" element={<AdminPage />} />
        <Route path="/profile" element={<ProfilePage username={username} />} />
        <Route path="/login" element={<Login setUser={props.setUser} />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/logout" element={<LogOut />} />
        <Route path="/about" element={<About />} />
        <Route path="/cart" element={<Cart />} />
        <Route path="/contact" element={<Contact />} />
        <Route path="/productdetails/:id" element={<ProductDetails />} />
      </Routes>
    </main>
  );
}
