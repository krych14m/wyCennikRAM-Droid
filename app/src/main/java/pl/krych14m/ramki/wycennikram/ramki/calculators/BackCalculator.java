package pl.krych14m.ramki.wycennikram.ramki.calculators;

import pl.krych14m.ramki.wycennikram.api.calculators.ArealProductCalculator;
import pl.krych14m.ramki.wycennikram.api.products.Product;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryKey;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryParameterNotFoundException;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryParametersProvider;
import pl.krych14m.ramki.wycennikram.ramki.products.Back;

public class BackCalculator extends ArealProductCalculator {

    private final AccessoryParametersProvider accessoryParametersProvider;

    public BackCalculator(AccessoryParametersProvider accessoryParametersProvider) {
        this.accessoryParametersProvider = accessoryParametersProvider;
    }

    @Override
    public double getSquareMeterPrice(Product product) throws AccessoryParameterNotFoundException {
        return accessoryParametersProvider.getAccessoryPrice(AccessoryKey.BACK_PRICE);
    }

    @Override
    public boolean isProductSupported(Product product) {
        return product.getClass().equals(Back.class);
    }
}
