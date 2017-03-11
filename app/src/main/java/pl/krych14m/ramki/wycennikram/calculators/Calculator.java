package pl.krych14m.ramki.wycennikram.calculators;

public interface Calculator {

    double getPrice(Product product) throws CalculatorException;

    boolean isProductSupported(Product product);

}
