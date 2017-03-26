package pl.krych14m.ramki.wycennikram.ramki.products;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class Frame extends PureFrame {

    private boolean glass;
    private boolean back;
    private boolean hook;
    private boolean badges;

    @Builder(toBuilder = true)
    public Frame(String profile, double x, double y, ColorType colorType, boolean glass, boolean back, boolean hook, boolean badges) {
        super(profile, x, y, colorType);
        this.glass = glass;
        this.back = back;
        this.hook = hook;
        this.badges = badges;
    }
}
