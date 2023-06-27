import { FlexDummy } from "../entities/FlexDummy";
import "./RightButtonLayout.css"

export function RightButtonLayout(props) {
    return (
        <div className="rightButtonLayout">
            <FlexDummy></FlexDummy>
            {props.children}
        </div>
    );
}