package pl.krych14m.ramki.wycennikram.ramki.products;

public enum ColorType {
    RAW("raw"),
    STAIN("stain"),
    OPAQUE("opaque"),
    WORN_OUT("worn_out");

    private final String key;

    ColorType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
