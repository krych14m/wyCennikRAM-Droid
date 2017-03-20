package pl.krych14m.ramki.wycennikram.api.calculators;

import pl.krych14m.ramki.wycennikram.api.products.Product;

public interface Calculator {

    double getPrice(Product product) throws CalculatorException;

    boolean isProductSupported(Product product);

}
