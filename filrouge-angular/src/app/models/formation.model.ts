import Product from "./product.model";
import { SousTheme } from "./sousTheme.model";

export default interface Formation extends Product {
    prix: number;
    description: string;
    img: string;
    sousThemeId: SousTheme;
}