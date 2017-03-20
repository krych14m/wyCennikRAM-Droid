package pl.krych14m.ramki.wycennikram.ramki.products;

import lombok.Builder;
import lombok.Data;
import pl.krych14m.ramki.wycennikram.api.products.Product;

@Data
@Builder
public class Frame implements Product {

    private final String profile;
    private final double x;
    private final double y;
    private final ColorType colorType;
    private final boolean glass;
    private final boolean back;
    private final boolean hook;
    private final boolean mounted;

}
