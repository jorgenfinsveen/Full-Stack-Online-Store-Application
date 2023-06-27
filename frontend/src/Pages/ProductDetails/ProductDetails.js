import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";
import { BigProductImage } from "../../components/img/BigProductImage";
import { ProductDetailsInfoAndButton } from "./ProductDetailsInfoAndButton";
import "./ProductDetails.css";
import { PageHeader } from "../../components/text/PageHeader";

export function ProductDetails() {
    const { id } = useParams();
    const [product, setProduct] = useState(null);

    // fetches data using swagger api
    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get(`https://group10.web-tek.ninja:8080/products/${id}`);
                const productData = response.data;
                setProduct(productData);
            } catch (error) {
                console.error(error);
            }
        };

        fetchData();
    }, [id]);

    if (product === null) {
        // Data is not yet fetched, show loading state or spinner
        return (
            <div className="productDetails">
                <div className="loadingDiv">
                    <PageHeader text="Loading..."></PageHeader>
                </div>
            </div>
        );
    }

    // Data is fetched, render the product details
    return (
        <div className="productDetails">
            <div className="productDetailsBox">
                <BigProductImage src={`data:image/png;base64,${product.image.imageData}`} alt={product.image.alt} />
                <ProductDetailsInfoAndButton product={product} />
            </div>
        </div>
    );
}