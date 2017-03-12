package pl.krych14m.ramki.wycennikram.priceproviders;

public interface AccessoryParametersProvider {

    String GLASS_PRICE_KEY ="glass_price";
    String BACK_PRICE_KEY = "back_price";

    double getAccessoryPrice(String accessoryKey);

}
