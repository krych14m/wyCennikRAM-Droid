package pl.krych14m.ramki.wycennikram.ramki.priceproviders;

import java.util.Set;

public interface ProfilePriceProvider {

    ProfilePrice getProfilePrice(String profile) throws ProfileNotFoundException;

    Set<String> getAvailableProfiles();

}
