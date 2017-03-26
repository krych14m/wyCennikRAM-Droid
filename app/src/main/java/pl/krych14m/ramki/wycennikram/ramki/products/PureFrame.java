package pl.krych14m.ramki.wycennikram.ramki.products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.NonFinal;
import pl.krych14m.ramki.wycennikram.api.products.TwoDimensionalProduct;

@Value
@NonFinal
@EqualsAndHashCode(callSuper = true)
public class PureFrame extends TwoDimensionalProduct {

    protected String profile;
    protected ColorType colorType;

    @Builder(builderMethodName = "pureFrameBuilder")
    public PureFrame(String profile, double x, double y, ColorType colorType) {
        super(x, y);
        this.profile = profile;
        this.colorType = colorType;
    }

}
