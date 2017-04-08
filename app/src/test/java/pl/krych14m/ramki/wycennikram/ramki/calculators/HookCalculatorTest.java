package pl.krych14m.ramki.wycennikram.ramki.calculators;

import org.junit.Before;
import org.junit.Test;

import pl.krych14m.ramki.wycennikram.api.calculators.CalculatorException;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryKey;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryParameterNotFoundException;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryParametersProvider;
import pl.krych14m.ramki.wycennikram.ramki.products.Hook;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HookCalculatorTest {

    private final static double BASE_HOOK_PRICE = 0.5;

    private HookCalculator hookCalculator;

    @Before
    public void before() throws AccessoryParameterNotFoundException {
        AccessoryParametersProvider accessoryPriceProvider = mock(AccessoryParametersProvider.class);
        when(accessoryPriceProvider.getAccessoryPrice(AccessoryKey.HOOK_PRICE)).thenReturn(BASE_HOOK_PRICE);
        this.hookCalculator = new HookCalculator(accessoryPriceProvider);
    }

    @Test
    public void hook_price_is_correct() throws CalculatorException {
        Hook hook = new Hook();

        double price = hookCalculator.getPrice(hook);

        assertEquals(BASE_HOOK_PRICE, price);
    }

}
