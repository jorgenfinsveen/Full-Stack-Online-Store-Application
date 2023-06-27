import { useState, useContext, useEffect } from "react";
import axios from "axios";
import { CartContext } from "../../context/CartContext";
import { ProductCard } from "../../components/entities/ProductCard";
import { PageHeader } from "../../components/text/PageHeader";
import { useParams } from 'react-router-dom';
import "./ProductPage.css";
import { ProductCardGrid } from "../../components/layout/ProductCardGrid";

/**
 * A react component responsible for displaying the products page.
 * 
 * @returns {JSX.Element} div element with its children
 */
export function ProductPage() {
    const { addToCart } = useContext(CartContext);
    const [products, setProducts] = useState([]);

    // fetches data once the component is mounted
    useEffect(() => {
        fetchData();
    }, []);

    // fetches product data using swagger api
    const fetchData = async () => {
        try {
            const response = await axios.get('https://group10.web-tek.ninja:8080/products');
            setProducts(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div className="productPage">
            <PageHeader text="Products" />
            <ProductCardGrid>
                {products.map((product) => (
                    <ProductCard key={product.id} product={product} />
                ))}
            </ProductCardGrid>
        </div>
    );
};