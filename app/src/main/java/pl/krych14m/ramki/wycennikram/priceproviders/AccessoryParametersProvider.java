package pl.krych14m.ramki.wycennikram.priceproviders;

public interface AccessoryParametersProvider {

    double getAccessoryPrice(AccessoryKey accessoryKey);

    enum AccessoryKey {
        GLASS_PRICE("glass_price"),
        BACK_PRICE("back_price");

        private final String key;

        AccessoryKey(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

}
