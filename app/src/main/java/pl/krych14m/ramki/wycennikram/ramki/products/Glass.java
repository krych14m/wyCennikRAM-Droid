package pl.krych14m.ramki.wycennikram.ramki.products;

import pl.krych14m.ramki.wycennikram.api.products.TwoDimensionalProduct;

public class Glass extends TwoDimensionalProduct {

    public Glass(double x, double y) {
        super(x, y);
    }

    @Override
    public String getName() {
        return toString();
    }
}
