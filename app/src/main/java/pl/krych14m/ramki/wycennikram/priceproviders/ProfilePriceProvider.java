package pl.krych14m.ramki.wycennikram.priceproviders;

import java.util.List;

public interface ProfilePriceProvider {

    double getBaseProfilePrice(String profile);

    List<String> getAvailableProfiles();

}
