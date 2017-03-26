package pl.krych14m.ramki.wycennikram.api.calculators;

import pl.krych14m.ramki.wycennikram.api.products.Product;
import pl.krych14m.ramki.wycennikram.api.products.TwoDimensionalProduct;

public abstract class ArealProductCalculator implements Calculator {

    @Override
    public double getPrice(Product product) throws CalculatorException {
        TwoDimensionalProduct product2d = (TwoDimensionalProduct) product;
        double x = product2d.getX();
        double y = product2d.getY();
        double squareMeterPrice = getSquareMeterPrice(product2d);
        return (x / 100) * (y / 100) * squareMeterPrice;
    }

    public abstract double getSquareMeterPrice(Product product);

}
