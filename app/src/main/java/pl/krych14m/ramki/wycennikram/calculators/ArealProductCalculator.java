package pl.krych14m.ramki.wycennikram.calculators;

import pl.krych14m.ramki.wycennikram.products.ArealProduct;
import pl.krych14m.ramki.wycennikram.products.Product;

public abstract class ArealProductCalculator implements Calculator {

    @Override
    public double getPrice(Product product) throws CalculatorException {
        ArealProduct arealProduct = (ArealProduct) product;
        double x = arealProduct.getX();
        double y = arealProduct.getY();
        double squareMeterPrice = getSquareMeterPrice();
        return (x / 100) * (y / 100) * squareMeterPrice;
    }

    public abstract double getSquareMeterPrice();

}
