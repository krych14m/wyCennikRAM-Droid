package pl.krych14m.ramki.wycennikram.ramki.products;

import java.text.MessageFormat;

import lombok.EqualsAndHashCode;
import lombok.Value;
import pl.krych14m.ramki.wycennikram.api.products.TwoDimensionalProduct;

@Value
@EqualsAndHashCode(callSuper = true)
public class PureFrame extends TwoDimensionalProduct {

    private String profile;
    private ColorType colorType;

    public PureFrame(String profile, double x, double y, ColorType colorType) {
        super(x, y);
        this.profile = profile;
        this.colorType = colorType;
    }

    @Override
    public String getName() {
        return MessageFormat.format("P{0} {1}x{2}", profile, x, y);
    }
}
