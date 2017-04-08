package pl.krych14m.ramki.wycennikram.ramki.priceproviders;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccessoryParametersProviderImpl implements AccessoryParametersProvider {

    public static final String TAG = AccessoryParametersProviderImpl.class.getSimpleName();

    private Map<AccessoryKey, Double> prices = new HashMap<>(5);

    public AccessoryParametersProviderImpl(InputStream inputStream) throws IOException {
        List<String> rawLines = IOUtils.readLines(inputStream, Charset.defaultCharset());

        if (rawLines.size() < 5) {
            throw new IOException("less than 5 lines in file");
        }

        List<Double> numbers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String rawLine = rawLines.get(i);
            String line = rawLine.trim().replace(',', '.');
            try {
                double number = Double.parseDouble(line);
                numbers.add(number);
            } catch (NumberFormatException e) {
                throw new IOException(line + " is not valid number", e);
            }
        }

        prices.put(AccessoryKey.BACK_PRICE, numbers.get(0));
        prices.put(AccessoryKey.GLASS_PRICE, numbers.get(1));
        prices.put(AccessoryKey.SINGLE_BADGE_PRICE, numbers.get(2));
        prices.put(AccessoryKey.BADGES_SPACE_CM, numbers.get(3));
        prices.put(AccessoryKey.HOOK_PRICE, numbers.get(4));
    }

    @Override
    public double getAccessoryPrice(AccessoryKey accessoryKey) throws AccessoryParameterNotFoundException {
        if (!prices.containsKey(accessoryKey)) {
            throw new AccessoryParameterNotFoundException("key " + accessoryKey.toString() + " not found");
        }
        return prices.get(accessoryKey);
    }

}
