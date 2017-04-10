package pl.krych14m.ramki.wycennikram.ramki.products;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private static final Pattern FRAME_STRING_PATTERN = Pattern.compile("P(\\d+)" //profile
            + " (\\d+[,.]?\\d+?)x(\\d+[,.]?\\d+?)" //dimensions
            + "( [BFP])?" //color
            + "( S?T?Z?f?)?"); //accessories

    public Frame(String frameString) {
        Matcher matcher = FRAME_STRING_PATTERN.matcher(frameString);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("string do not match frame string pattern:" + frameString);
        }

        profile = matcher.group(1);

        x = Double.valueOf(matcher.group(2).replace(',', '.'));
        y = Double.valueOf(matcher.group(3).replace(',', '.'));

        String colorGroup = matcher.group(4);
        String colorString = colorGroup != null ? colorGroup.trim() : "";
        if (colorString.equals("B")) {
            colorType = ColorType.STAIN;
        } else if (colorString.equals("F")) {
            colorType = ColorType.OPAQUE;
        } else if (colorString.equals("P")) {
            colorType = ColorType.WORN_OUT;
        } else {
            colorType = ColorType.RAW;
        }

        String accessoriesGroup = matcher.group(5);
        if (accessoriesGroup != null) {
            String accessoriesString = accessoriesGroup.trim();
            glass = accessoriesString.contains("S");
            back = accessoriesString.contains("T");
            hook = accessoriesString.contains("Z");
            badges = accessoriesString.contains("f");
        } else {
            glass = back = hook = badges = false;
        }
    }

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
