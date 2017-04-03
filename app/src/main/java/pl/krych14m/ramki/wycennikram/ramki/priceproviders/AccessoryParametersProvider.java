package pl.krych14m.ramki.wycennikram.ramki.priceproviders;

public interface AccessoryParametersProvider {

    double getAccessoryPrice(AccessoryKey accessoryKey) throws AccessoryParameterNotFoundException;

}
