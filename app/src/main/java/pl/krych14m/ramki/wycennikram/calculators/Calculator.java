package pl.krych14m.ramki.wycennikram.calculators;

import pl.krych14m.ramki.wycennikram.products.Product;

public interface Calculator {

    double getPrice(Product product) throws CalculatorException;

    boolean isProductSupported(Product product);

}