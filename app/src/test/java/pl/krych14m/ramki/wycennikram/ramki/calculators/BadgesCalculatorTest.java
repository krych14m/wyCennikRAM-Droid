package pl.krych14m.ramki.wycennikram.ramki.calculators;

import org.junit.Before;
import org.junit.Test;

import pl.krych14m.ramki.wycennikram.api.calculators.CalculatorException;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryKey;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryParameterNotFoundException;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryParametersProvider;
import pl.krych14m.ramki.wycennikram.ramki.products.Badges;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BadgesCalculatorTest {

    private final static double BASE_SINGLE_BADGE_PRICE = 0.09;
    private final static double BASE_BADGES_SPACE_CM = 15;

    private BadgesCalculator badgesCalculator;

    @Before
    public void before() throws AccessoryParameterNotFoundException {
        AccessoryParametersProvider accessoryPriceProvider = mock(AccessoryParametersProvider.class);
        when(accessoryPriceProvider.getAccessoryPrice(AccessoryKey.SINGLE_BADGE_PRICE)).thenReturn(BASE_SINGLE_BADGE_PRICE);
        when(accessoryPriceProvider.getAccessoryPrice(AccessoryKey.BADGES_SPACE_CM)).thenReturn(BASE_BADGES_SPACE_CM);
        this.badgesCalculator = new BadgesCalculator(accessoryPriceProvider);
    }

    @Test
    public void badges_price_is_correct() throws CalculatorException {
        Badges badges = new Badges(20.0, 30.0);

        double price = badgesCalculator.getPrice(badges);

        assertEquals(2 * (20.0 + 30.0) / 100 * BASE_SINGLE_BADGE_PRICE * 100 / BASE_BADGES_SPACE_CM, price);
    }

}
