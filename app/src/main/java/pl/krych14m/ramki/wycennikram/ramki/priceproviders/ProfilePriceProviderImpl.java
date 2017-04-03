package pl.krych14m.ramki.wycennikram.ramki.priceproviders;

import org.apache.commons.io.IOUtils;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProfilePriceProviderImpl implements ProfilePriceProvider {

    public static final String TAG = ProfilePriceProviderImpl.class.getSimpleName();

    private Map<String, ProfilePrice> prices = new HashMap<>();

    public ProfilePriceProviderImpl(InputStream inputStream) throws IOException {
        List<String> lines = IOUtils.readLines(inputStream, Charset.defaultCharset());

        for (String line : lines) {
            String[] items = line.trim().split(" ");
            if (items.length != 2) {
                Log.e(TAG, MessageFormat.format("profile line parsing error: '{0}'", line));
            }
            String profile = items[0].trim();
            String priceStr = items[1].trim().replace(',', '.');
			try {
				double price = Double.parseDouble(priceStr);
				prices.put(profile, new ProfilePriceImpl(price));
			} catch (NumberFormatException e) {
				throw new IOException(line + " is not valid number", e);
			}
		}
    }

    @Override
    public ProfilePrice getProfilePrice(String profile) throws ProfileNotFoundException {
        if (!prices.containsKey(profile)) {
            throw new ProfileNotFoundException("profile " + profile + " not found");
        }
        return prices.get(profile);
    }

    @Override
    public Set<String> getAvailableProfiles() {
        return prices.keySet();
    }

}
