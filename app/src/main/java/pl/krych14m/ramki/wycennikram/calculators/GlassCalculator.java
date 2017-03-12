package pl.krych14m.ramki.wycennikram.calculators;

import pl.krych14m.ramki.wycennikram.priceproviders.AccessoryParametersProvider;
import pl.krych14m.ramki.wycennikram.products.Glass;
import pl.krych14m.ramki.wycennikram.products.Product;

public class GlassCalculator implements Calculator {

    private final AccessoryParametersProvider accessoryPriceProvider;

    public GlassCalculator(AccessoryParametersProvider accessoryPriceProvider) {
        this.accessoryPriceProvider = accessoryPriceProvider;
    }

    @Override
    public double getPrice(Product product) throws CalculatorException {
        Glass glass = (Glass) product;
        double glassPrice = accessoryPriceProvider.getAccessoryPrice(AccessoryParametersProvider.GLASS_PRICE_KEY);
        double x = glass.getX() / 100.0;
        double y = glass.getY() / 100.0;
        return glassPrice * x * y;
    }

    @Override
    public boolean isProductSupported(Product product) {
        return product.getClass().equals(Glass.class);
    }
}
