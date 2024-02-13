import Product from "./product.model";

export default interface Formation extends Product {
    prix: number;
    description: string;
}