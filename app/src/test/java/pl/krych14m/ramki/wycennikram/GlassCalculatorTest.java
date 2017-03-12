package pl.krych14m.ramki.wycennikram;

import org.junit.Before;
import org.junit.Test;

import pl.krych14m.ramki.wycennikram.calculators.CalculatorException;
import pl.krych14m.ramki.wycennikram.calculators.GlassCalculator;
import pl.krych14m.ramki.wycennikram.priceproviders.AccessoryParametersProvider;
import pl.krych14m.ramki.wycennikram.products.Glass;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.krych14m.ramki.wycennikram.priceproviders.AccessoryParametersProvider.GLASS_PRICE_KEY;

public class GlassCalculatorTest {

    private final static double BASE_GLASS_PRICE = 100;

    private GlassCalculator glassCalculator;

    @Before
    public void before() {
        AccessoryParametersProvider accessoryPriceProvider = mock(AccessoryParametersProvider.class);
        when(accessoryPriceProvider.getAccessoryPrice(GLASS_PRICE_KEY)).thenReturn(BASE_GLASS_PRICE);
        this.glassCalculator = new GlassCalculator(accessoryPriceProvider);
    }

    @Test
    public void glass_price_is_correct() throws CalculatorException {
        Glass glass = new Glass(30.0, 40.0);

        double price = glassCalculator.getPrice(glass);

        assertEquals((30.0 / 100.0) * (40.0 / 100.0) * BASE_GLASS_PRICE, price);
    }

}
