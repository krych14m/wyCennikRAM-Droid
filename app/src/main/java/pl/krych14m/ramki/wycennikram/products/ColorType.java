package pl.krych14m.ramki.wycennikram.products;

enum ColorType {
    RAW("raw"),
    STAIN("stain"),
    OPAQUE("opaque");

    private final String key;

    ColorType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
