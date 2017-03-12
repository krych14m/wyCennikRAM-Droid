package pl.krych14m.ramki.wycennikram.priceproviders;

public interface AccessoryParametersProvider {

    String GLASS_PRICE_KEY ="glass_price";

    double getAccessoryPrice(String accessoryKey);

}
