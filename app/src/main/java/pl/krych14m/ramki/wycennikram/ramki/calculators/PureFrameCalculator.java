package pl.krych14m.ramki.wycennikram.ramki.calculators;


import org.apache.commons.math3.util.Precision;

import pl.krych14m.ramki.wycennikram.api.calculators.Calculator;
import pl.krych14m.ramki.wycennikram.api.calculators.CalculatorException;
import pl.krych14m.ramki.wycennikram.api.products.Product;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.ProfileNotFoundException;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.ProfilePrice;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.ProfilePriceProvider;
import pl.krych14m.ramki.wycennikram.ramki.products.PureFrame;

class PureFrameCalculator implements Calculator {

    private final ProfilePriceProvider profilePriceProvider;

    PureFrameCalculator(ProfilePriceProvider profilePriceProvider) {
        this.profilePriceProvider = profilePriceProvider;
    }

    @Override
    public double getPrice(Product product) throws CalculatorException {
        try {
            PureFrame frame = (PureFrame) product;
            double perimeter = getPerimeter(frame);
            ProfilePrice profilePrice = getProfilePrice(frame);

            double price;

            if (perimeter >= 1) {
                price = profilePrice.getBasePrice() * perimeter;
            } else {
                price = profilePrice.getLessThanMeterPrice() * perimeter + profilePrice.getAddonPrice();
            }

            switch (frame.getColorType()) {
                case STAIN:
                    price *= 1.5;
                    break;
                case OPAQUE:
                    price *= 1.5 * 1.2;
                    break;
                case WORN_OUT:
                    price *= 1.5 * 1.4;
                    break;
            }

            return Precision.round(price, 2);
        } catch (ProfileNotFoundException e) {
            throw new CalculatorException(e);
        }
    }

    @Override
    public boolean isProductSupported(Product product) {
        return PureFrame.class.equals(product.getClass());
    }

    private double getPerimeter(PureFrame frame) {
        return 2.0 * (frame.getX() + frame.getY()) / 100.0;
    }

    private ProfilePrice getProfilePrice(PureFrame frame) throws ProfileNotFoundException {
        return profilePriceProvider.getProfilePrice(frame.getProfile());
    }

}
