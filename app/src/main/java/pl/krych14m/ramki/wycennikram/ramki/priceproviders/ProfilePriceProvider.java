package pl.krych14m.ramki.wycennikram.ramki.priceproviders;

import java.util.List;

public interface ProfilePriceProvider {

    ProfilePrice getProfilePrice(String profile);

    List<String> getAvailableProfiles();

}
