package pl.krych14m.ramki.wycennikram.ramki.priceproviders;

public interface ProfilePrice {

    double getBasePrice();

    double getLessThanMeterPrice();

    double getAddonPrice();

}
