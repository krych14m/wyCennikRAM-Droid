package pl.krych14m.ramki.wycennikram.ramki.products;

public enum ColorType {
    RAW(""),
    STAIN("B"),
    OPAQUE("F"),
    WORN_OUT("P");

    private final String businessKey;

    ColorType(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getBusinessKey() {
        return businessKey;
    }

}
