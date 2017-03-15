package pl.krych14m.ramki.wycennikram.products;

import lombok.Builder;
import lombok.Data;

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
