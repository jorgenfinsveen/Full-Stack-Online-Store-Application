import "./PageHeader.css"

/**
 * A header for pages.
 * 
 * @param {*} props the text displayed
 * @returns  {JSX.Element} a header with the desired text
 */
export function PageHeader(props) {
    return (
        <h1 className="pageHeader">{props.text}</h1>
    );
}