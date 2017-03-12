package pl.krych14m.ramki.wycennikram.calculators;

import pl.krych14m.ramki.wycennikram.products.Frame;
import pl.krych14m.ramki.wycennikram.products.Product;

public class FrameCalculator implements Calculator {

    @Override
    public double getPrice(Product frame) {
        //TODO
        throw new Error("not yet implemented");
    }

    @Override
    public boolean isProductSupported(Product product) {
        return product.getClass().equals(Frame.class);
    }

}
