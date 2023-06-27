import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";

export function LogOut({ props }) {
  const navigate = useNavigate();
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  // Handle logout action
  const handleLogout = () => {
    // Perform any necessary logout logic here
    // For example, clear user session, reset state, etc.
    // Update the login status to false
    setIsLoggedIn(isLoggedIn === false);
    // Navigate to the login page after logout
    navigate("/login");
  };

  return (
    <div>
      <h1>Welcome to Mocha!</h1>
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
}
