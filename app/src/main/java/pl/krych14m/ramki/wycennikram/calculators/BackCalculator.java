package pl.krych14m.ramki.wycennikram.calculators;

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
        return accessoryPriceProvider.getAccessoryPrice(AccessoryParametersProvider.BACK_PRICE_KEY);
    }

    @Override
    public boolean isProductSupported(Product product) {
        return product.getClass().equals(Back.class);
    }
}
