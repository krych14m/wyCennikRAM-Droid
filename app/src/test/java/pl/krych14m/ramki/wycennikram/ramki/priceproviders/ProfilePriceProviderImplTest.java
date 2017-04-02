package pl.krych14m.ramki.wycennikram.ramki.priceproviders;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class ProfilePriceProviderImplTest {

    private static final String profilePricesFileContent = "10 10,0\n"
            + "20 20,0\n";

    private ProfilePriceProvider profilePriceProvider;


    @Before
    public void before() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(profilePricesFileContent.getBytes(Charset.defaultCharset()));
        profilePriceProvider = new ProfilePriceProviderImpl(inputStream);
    }

    @Test
    public void profile_price_from_file_is_returned() throws ProfileNotFoundException {
        ProfilePrice profilePrice10 = profilePriceProvider.getProfilePrice("10");
        ProfilePrice profilePrice20 = profilePriceProvider.getProfilePrice("20");

        assertEquals(10.0, profilePrice10.getLessThanMeterPrice());
        assertEquals(20.0, profilePrice20.getLessThanMeterPrice());
    }

    @Test(expected = ProfileNotFoundException.class)
    public void profile_price_out_of_file_is_not_returned() throws ProfileNotFoundException {
        profilePriceProvider.getProfilePrice("30");
    }

    @Test
    public void provider_knows_all_profiles_from_file() {
        Set<String> profiles = profilePriceProvider.getAvailableProfiles();

        assertEquals(2, profiles.size());
        assertTrue(profiles.contains("10"));
        assertTrue(profiles.contains("20"));
    }

}
