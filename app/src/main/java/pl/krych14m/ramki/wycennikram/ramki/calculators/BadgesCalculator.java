package pl.krych14m.ramki.wycennikram.ramki.calculators;

import pl.krych14m.ramki.wycennikram.api.calculators.PerimetralProductCalculator;
import pl.krych14m.ramki.wycennikram.api.products.Product;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryKey;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryParametersProvider;
import pl.krych14m.ramki.wycennikram.ramki.products.Badges;

public class BadgesCalculator extends PerimetralProductCalculator {

    private final AccessoryParametersProvider accessoryParametersProvider;

    public BadgesCalculator(AccessoryParametersProvider accessoryParametersProvider) {
        this.accessoryParametersProvider = accessoryParametersProvider;
    }

    @Override
    public boolean isProductSupported(Product product) {
        return Badges.class.equals(product.getClass());
    }

    @Override
    public double getMeterPrice(Product product) {
        double singleBadgePrice = accessoryParametersProvider.getAccessoryPrice(AccessoryKey.SINGLE_BADGE_PRICE);
        double badgeSpaceCm = accessoryParametersProvider.getAccessoryPrice(AccessoryKey.BADGES_SPACE_CM);
        return singleBadgePrice * 100 / badgeSpaceCm;
    }

}
