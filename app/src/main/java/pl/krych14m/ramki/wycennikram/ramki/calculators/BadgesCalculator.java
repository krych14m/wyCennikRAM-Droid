package pl.krych14m.ramki.wycennikram.ramki.calculators;

import pl.krych14m.ramki.wycennikram.api.calculators.Calculator;
import pl.krych14m.ramki.wycennikram.api.calculators.CalculatorException;
import pl.krych14m.ramki.wycennikram.api.products.Product;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryKey;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryParameterNotFoundException;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryParametersProvider;
import pl.krych14m.ramki.wycennikram.ramki.products.Badges;

public class BadgesCalculator implements Calculator {

    private final AccessoryParametersProvider accessoryParametersProvider;

    public BadgesCalculator(AccessoryParametersProvider accessoryParametersProvider) {
        this.accessoryParametersProvider = accessoryParametersProvider;
    }

    @Override
    public double getPrice(Product product) throws CalculatorException {
        Badges badges = (Badges) product;
		double perimeter = getPerimeter(badges);
		double meterPrice;
		try {
			meterPrice = getMeterPrice(badges);
		} catch (AccessoryParameterNotFoundException e) {
			throw new CalculatorException("meter price error", e);
		}
		return perimeter * meterPrice;
	}

    @Override
    public boolean isProductSupported(Product product) {
        return Badges.class.equals(product.getClass());
    }

	private double getMeterPrice(Badges badges) throws AccessoryParameterNotFoundException {
		double singleBadgePrice = accessoryParametersProvider.getAccessoryPrice(AccessoryKey.SINGLE_BADGE_PRICE);
		double badgeSpaceCm = accessoryParametersProvider.getAccessoryPrice(AccessoryKey.BADGES_SPACE_CM);
        return singleBadgePrice * 100 / badgeSpaceCm;
    }

    private double getPerimeter(Badges badges) {
        double x = badges.getX();
        double y = badges.getY();
        return 2 * (x + y) / 100;
    }

}
