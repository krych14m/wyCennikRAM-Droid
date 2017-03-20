package pl.krych14m.ramki.wycennikram.ramki.priceproviders;

public enum AccessoryKey {
    GLASS_PRICE("glass_price"),
    BACK_PRICE("back_price"),
    HOOK_PRICE("hook_price");

    private final String key;

    AccessoryKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}