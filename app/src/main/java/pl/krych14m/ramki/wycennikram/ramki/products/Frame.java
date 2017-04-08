package pl.krych14m.ramki.wycennikram.ramki.products;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Setter;
import lombok.Value;
import pl.krych14m.ramki.wycennikram.api.products.Product;

@Value
@Builder(toBuilder = true)
public class Frame implements Product {

    @Builder.Default
    @Setter(AccessLevel.NONE)
    private String profile = "";

    @Builder.Default
    @Setter(AccessLevel.NONE)
    private double x = 0;

    @Builder.Default
    @Setter(AccessLevel.NONE)
    private double y = 0;

    @Builder.Default
    @Setter(AccessLevel.NONE)
    private ColorType colorType = ColorType.RAW;

    @Builder.Default
    @Setter(AccessLevel.NONE)
    private boolean glass = false;

    @Builder.Default
    @Setter(AccessLevel.NONE)
    private boolean back = false;

    @Builder.Default
    @Setter(AccessLevel.NONE)
    private boolean hook = false;

    @Builder.Default
    @Setter(AccessLevel.NONE)
    private boolean badges = false;

    public PureFrame getPureFrame() {
        return new PureFrame(profile, x, y, colorType);
    }

    @Override
    public String getName() {
        String pureFrame = getPureFrame().getName();
        String colorSection = colorType.getBusinessKey();
        String accessoriesSection = ""
                + (glass ? "S" : "")
                + (back ? "T" : "")
                + (hook ? "Z" : "")
                + (badges ? "f" : "");
        return pureFrame + followingSpace(colorSection) + followingSpace(accessoriesSection);
    }

    private static String followingSpace(String s) {
        if (s.trim().equals("")) {
            return "";
        }
        return " " + s;
    }
}
