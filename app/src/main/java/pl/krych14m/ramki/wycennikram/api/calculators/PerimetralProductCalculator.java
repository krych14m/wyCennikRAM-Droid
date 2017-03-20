package pl.krych14m.ramki.wycennikram.api.calculators;

import pl.krych14m.ramki.wycennikram.api.products.TwoDimensionalProduct;
import pl.krych14m.ramki.wycennikram.api.products.Product;

public abstract class PerimetralProductCalculator implements Calculator {
    @Override
    public double getPrice(Product product) throws CalculatorException {
        TwoDimensionalProduct product2d = (TwoDimensionalProduct) product;
        double x = product2d.getX();
        double y = product2d.getY();
        double squareMeterPrice = getMeterPrice(product);
        return (x / 100) * (y / 100) * squareMeterPrice;
    }

    public abstract double getMeterPrice(Product product);

}
