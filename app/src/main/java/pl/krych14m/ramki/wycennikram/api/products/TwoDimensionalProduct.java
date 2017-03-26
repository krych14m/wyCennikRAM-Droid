package pl.krych14m.ramki.wycennikram.api.products;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@NonFinal
@AllArgsConstructor
public abstract class TwoDimensionalProduct implements Product {

    protected double x;
    protected double y;

}
