package pl.krych14m.ramki.wycennikram.calculators;

import java.util.Collections;
import java.util.List;

import pl.krych14m.ramki.wycennikram.products.Product;

public class MultiProductCalculator implements Calculator {

    private final List<Calculator> calculators;

    public MultiProductCalculator(List<Calculator> calculators) {
        this.calculators = Collections.unmodifiableList(calculators);
    }

    @Override
    public double getPrice(Product product) throws CalculatorException {
        Calculator calc = getCalculatorForProduct(product);
        if (calc == null) {
            Class<?> productClass = product.getClass();
            throw new CalculatorException("product calculator not found for " + productClass.getCanonicalName());
        }
        return calc.getPrice(product);
    }

    @Override
    public boolean isProductSupported(Product product) {
        return getCalculatorForProduct(product) != null;
    }

    private Calculator getCalculatorForProduct(Product product) {
        for (Calculator calc : calculators) {
            if (calc.isProductSupported(product)) {
                return calc;
            }
        }
        return null;
    }

}
