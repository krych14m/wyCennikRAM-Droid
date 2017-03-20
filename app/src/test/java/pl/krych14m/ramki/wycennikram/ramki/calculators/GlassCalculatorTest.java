package pl.krych14m.ramki.wycennikram.ramki.calculators;

import org.junit.Before;
import org.junit.Test;

import pl.krych14m.ramki.wycennikram.api.calculators.CalculatorException;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryKey;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryParametersProvider;
import pl.krych14m.ramki.wycennikram.ramki.products.Glass;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GlassCalculatorTest {

    private final static double BASE_GLASS_PRICE = 100;

    private GlassCalculator glassCalculator;

    @Before
    public void before() {
        AccessoryParametersProvider accessoryPriceProvider = mock(AccessoryParametersProvider.class);
        when(accessoryPriceProvider.getAccessoryPrice(AccessoryKey.GLASS_PRICE)).thenReturn(BASE_GLASS_PRICE);
        this.glassCalculator = new GlassCalculator(accessoryPriceProvider);
    }

    @Test
    public void glass_price_is_correct() throws CalculatorException {
        Glass glass = new Glass(30.0, 40.0);

        double price = glassCalculator.getPrice(glass);

        assertEquals((30.0 / 100.0) * (40.0 / 100.0) * BASE_GLASS_PRICE, price);
    }

}
