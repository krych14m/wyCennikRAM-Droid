package pl.krych14m.ramki.wycennikram.ramki.products;

import lombok.AllArgsConstructor;
import lombok.Value;
import pl.krych14m.ramki.wycennikram.api.products.Product;

@Value
@AllArgsConstructor
public class Frame implements Product {

    private String profile;
    private double x;
    private double y;
    private ColorType colorType;
    private boolean glass;
    private boolean back;
    private boolean hook;
    private boolean badges;

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
