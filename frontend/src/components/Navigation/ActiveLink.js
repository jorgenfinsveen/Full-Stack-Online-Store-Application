import { NavLink } from "react-router-dom";


export function ActiveLink(props) {
  return (
    <NavLink
      to={props.to}
      className={({ isActive }) => (isActive ? "selected" : "")}
    >
      {props.children}
    </NavLink>
  );
}