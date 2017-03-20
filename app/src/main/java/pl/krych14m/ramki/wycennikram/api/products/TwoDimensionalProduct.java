package pl.krych14m.ramki.wycennikram.api.products;

import lombok.Data;

@Data
public abstract class TwoDimensionalProduct implements Product {

    private final double x;
    private final double y;

}
