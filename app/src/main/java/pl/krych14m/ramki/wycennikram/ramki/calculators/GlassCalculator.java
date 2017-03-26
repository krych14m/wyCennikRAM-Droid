package pl.krych14m.ramki.wycennikram.ramki.calculators;

import pl.krych14m.ramki.wycennikram.api.calculators.ArealProductCalculator;
import pl.krych14m.ramki.wycennikram.api.products.Product;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryKey;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryParametersProvider;
import pl.krych14m.ramki.wycennikram.ramki.products.Glass;

public class GlassCalculator extends ArealProductCalculator {

    private final AccessoryParametersProvider accessoryPriceProvider;

    public GlassCalculator(AccessoryParametersProvider accessoryPriceProvider) {
        this.accessoryPriceProvider = accessoryPriceProvider;
    }

    @Override
    public double getSquareMeterPrice(Product product) {
        return accessoryPriceProvider.getAccessoryPrice(AccessoryKey.GLASS_PRICE);
    }

    @Override
    public boolean isProductSupported(Product product) {
        return product.getClass().equals(Glass.class);
    }
}
