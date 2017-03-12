package pl.krych14m.ramki.wycennikram;

import org.junit.Before;
import org.junit.Test;

import pl.krych14m.ramki.wycennikram.calculators.BackCalculator;
import pl.krych14m.ramki.wycennikram.calculators.CalculatorException;
import pl.krych14m.ramki.wycennikram.priceproviders.AccessoryParametersProvider;
import pl.krych14m.ramki.wycennikram.products.Back;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.krych14m.ramki.wycennikram.priceproviders.AccessoryParametersProvider.BACK_PRICE_KEY;

public class BackCalculatorTest {

    private final static double BASE_BACK_PRICE = 100;

    private BackCalculator backCalculator;

    @Before
    public void before() {
        AccessoryParametersProvider accessoryPriceProvider = mock(AccessoryParametersProvider.class);
        when(accessoryPriceProvider.getAccessoryPrice(BACK_PRICE_KEY)).thenReturn(BASE_BACK_PRICE);
        this.backCalculator = new BackCalculator(accessoryPriceProvider);
    }

    @Test
    public void back_price_is_correct() throws CalculatorException {
        Back back = new Back(30.0, 40.0);

        double price = backCalculator.getPrice(back);

        assertEquals((30.0 / 100.0) * (40.0 / 100.0) * BASE_BACK_PRICE, price);
    }

}
