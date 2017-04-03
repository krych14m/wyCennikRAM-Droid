package pl.krych14m.ramki.wycennikram.ramki.priceproviders;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static junit.framework.Assert.assertEquals;

public class AccessoryParametersProviderImplTest {

	private final static String ACCESSORY_PRICES_FILE = "1.0\n" + "2,0\n" + "3.0\n" + "4,0\n" + "5.0\n";

	private AccessoryParametersProvider accessoryParametersProvider;

	@Before
	public void before() throws IOException {
		accessoryParametersProvider = createAccessoryParametersProviderFromString(ACCESSORY_PRICES_FILE);
	}

	@Test
	public void back_price_test() throws AccessoryParameterNotFoundException {
		assertPrice(AccessoryKey.BACK_PRICE, 1);
	}

	@Test
	public void glass_price_test() throws AccessoryParameterNotFoundException {
		assertPrice(AccessoryKey.GLASS_PRICE, 2);
	}

	@Test
	public void single_badge_price_test() throws AccessoryParameterNotFoundException {
		assertPrice(AccessoryKey.SINGLE_BADGE_PRICE, 3);
	}

	@Test
	public void badge_space_test() throws AccessoryParameterNotFoundException {
		assertPrice(AccessoryKey.BADGES_SPACE_CM, 4);
	}

	@Test
	public void hook_price_test() throws AccessoryParameterNotFoundException {
		assertPrice(AccessoryKey.HOOK_PRICE, 5);
	}

	@Test(expected = IOException.class)
	public void less_than_5_lines_in_file() throws IOException {
		createAccessoryParametersProviderFromString("1.0");
	}

	@Test(expected = IOException.class)
	public void bad_number() throws IOException {
		createAccessoryParametersProviderFromString("abc\n" + ACCESSORY_PRICES_FILE);
	}

	private AccessoryParametersProviderImpl createAccessoryParametersProviderFromString(String fileContentString) throws IOException {
		InputStream inputStream = new ByteArrayInputStream(fileContentString.getBytes(Charset.defaultCharset()));
		return new AccessoryParametersProviderImpl(inputStream);
	}

	private void assertPrice(AccessoryKey accessoryKey, double expectedPrice) throws AccessoryParameterNotFoundException {
		double price = accessoryParametersProvider.getAccessoryPrice(accessoryKey);
		assertEquals(expectedPrice, price);
	}
}
