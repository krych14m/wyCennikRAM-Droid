package pl.krych14m.ramki.wycennikram.ramki.priceproviders;

import org.apache.commons.math3.util.Precision;

public class ProfilePriceImpl implements ProfilePrice {

    private final double stripPrice;

    public ProfilePriceImpl(double stripPrice) {
        this.stripPrice = Precision.round(stripPrice, 2);
    }

    @Override
    public double getLessThanMeterPrice() {
        return stripPrice;
    }

    @Override
    public double getBasePrice() {
        return Precision.round(getLessThanMeterPrice() / 0.7, 2);
    }

    @Override
    public double getAddonPrice() {
        return getBasePrice() - getLessThanMeterPrice();
    }

}
