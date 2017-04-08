package pl.krych14m.ramki.wycennikram.ramki.products;

import pl.krych14m.ramki.wycennikram.api.products.TwoDimensionalProduct;

public class Badges extends TwoDimensionalProduct {

    public Badges(double x, double y) {
        super(x, y);
    }

    @Override
    public String getName() {
        return toString();
    }
}
