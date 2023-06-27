import "./ProductCardGrid.css"

export function ProductCardGrid(props) {
    return (
        <div className="productCardGrid">
            {props.children}
        </div>
    );
}