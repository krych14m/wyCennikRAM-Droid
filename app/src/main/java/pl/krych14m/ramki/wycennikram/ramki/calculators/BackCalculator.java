package pl.krych14m.ramki.wycennikram.ramki.calculators;

import pl.krych14m.ramki.wycennikram.api.calculators.ArealProductCalculator;
import pl.krych14m.ramki.wycennikram.api.products.Product;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryKey;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryParametersProvider;
import pl.krych14m.ramki.wycennikram.ramki.products.Back;

public class BackCalculator extends ArealProductCalculator {

    private final AccessoryParametersProvider accessoryPriceProvider;

    public BackCalculator(AccessoryParametersProvider accessoryPriceProvider) {
        this.accessoryPriceProvider = accessoryPriceProvider;
    }

    @Override
    public double getSquareMeterPrice(Product product) {
        return accessoryPriceProvider.getAccessoryPrice(AccessoryKey.BACK_PRICE);
    }

    @Override
    public boolean isProductSupported(Product product) {
        return product.getClass().equals(Back.class);
    }
}
