package pl.krych14m.ramki.wycennikram.calculators.ramkicalculators;

import pl.krych14m.ramki.wycennikram.calculators.api.ArealProductCalculator;
import pl.krych14m.ramki.wycennikram.priceproviders.AccessoryKey;
import pl.krych14m.ramki.wycennikram.priceproviders.AccessoryParametersProvider;
import pl.krych14m.ramki.wycennikram.products.Back;
import pl.krych14m.ramki.wycennikram.products.Product;

public class BackCalculator extends ArealProductCalculator {

    private final AccessoryParametersProvider accessoryPriceProvider;

    public BackCalculator(AccessoryParametersProvider accessoryPriceProvider) {
        this.accessoryPriceProvider = accessoryPriceProvider;
    }

    @Override
    public double getSquareMeterPrice() {
        return accessoryPriceProvider.getAccessoryPrice(AccessoryKey.BACK_PRICE);
    }

    @Override
    public boolean isProductSupported(Product product) {
        return product.getClass().equals(Back.class);
    }
}
