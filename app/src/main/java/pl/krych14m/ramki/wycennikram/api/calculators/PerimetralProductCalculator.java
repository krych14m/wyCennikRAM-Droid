package pl.krych14m.ramki.wycennikram.api.calculators;

import pl.krych14m.ramki.wycennikram.api.products.Product;
import pl.krych14m.ramki.wycennikram.api.products.TwoDimensionalProduct;

public abstract class PerimetralProductCalculator implements Calculator {
    @Override
    public double getPrice(Product product) throws CalculatorException {
        TwoDimensionalProduct product2d = (TwoDimensionalProduct) product;
        double x = product2d.getX();
        double y = product2d.getY();
        double meterPrice = getMeterPrice(product);
        return 2 * (x + y) / 100 * meterPrice;
    }

    public abstract double getMeterPrice(Product product);

}
