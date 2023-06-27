import * as React from "react";
import Accordion from "@mui/material/Accordion";
import AccordionSummary from "@mui/material/AccordionSummary";
import AccordionDetails from "@mui/material/AccordionDetails";
import Typography from "@mui/material/Typography";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import "./Footer.css";

/**
 * Creates the footer for the application
 * with Accordian mui component library.
 *
 * @returns {JSX.Element}
 * @constructor
 */
export function Footer() {
  return (
    <footer>
      This website is a result of a university group project, performed in
      the course IDATA2301 Web technologies, at NTNU. All the information
      provided here is a result of imagination. Any resemblance with real
      companies or products is a coincidence.
    </footer>
  );
}
