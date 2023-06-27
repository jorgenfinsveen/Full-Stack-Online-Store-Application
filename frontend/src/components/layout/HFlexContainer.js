import "./HFlexContainer.css"

/**
 * Layout component which flexes in the horizontal axis.
 * 
 * @param {*} props the children of the container
 * @returns {JSX.Element} container with its children
 */
export function HFlexContainer(props) {
    return (
        <div className="hFlexContainer">{props.children}</div>
    );
}