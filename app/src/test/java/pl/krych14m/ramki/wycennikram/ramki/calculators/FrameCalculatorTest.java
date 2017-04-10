package pl.krych14m.ramki.wycennikram.ramki.calculators;

import org.apache.commons.math3.util.Precision;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.LinkedList;

import lombok.ToString;
import lombok.Value;
import pl.krych14m.ramki.wycennikram.api.calculators.CalculatorException;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryKey;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryParameterNotFoundException;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryParametersProvider;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.ProfileNotFoundException;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.ProfilePriceImpl;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.ProfilePriceProvider;
import pl.krych14m.ramki.wycennikram.ramki.products.Frame;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class FrameCalculatorTest {

    private static final double STRIP_PRICE = PureFrameCalculatorTest.STRIP_PRICE;
    private static final double LESS_THAN_METER_PRICE = PureFrameCalculatorTest.LESS_THAN_METER_PRICE;
    private static final double ADDON_PRICE = PureFrameCalculatorTest.ADDON_PRICE;

    private static final double GLASS_PRICE = 30;
    private static final double BACK_PRICE = 10;
    private static final double BADGES_SPACE_CM = 100;
    private static final double SINGLE_BADGE_PRICE = 1;
    private static final double HOOK_PRICE = 0.5;

    private final String frameString;
    private final double price;

    private static FrameCalculator frameCalculator;

    public FrameCalculatorTest(TestParameter parameter) {
        this.frameString = parameter.getFrameString();
        this.price = parameter.getPrice();
    }

    @BeforeClass
    public static void before() throws ProfileNotFoundException, AccessoryParameterNotFoundException {
        ProfilePriceProvider profilePriceProvider = mock(ProfilePriceProvider.class);
        when(profilePriceProvider.getProfilePrice("10")).thenReturn(new ProfilePriceImpl(STRIP_PRICE));

        AccessoryParametersProvider accessoryParametersProvider = mock(AccessoryParametersProvider.class);
        when(accessoryParametersProvider.getAccessoryPrice(AccessoryKey.GLASS_PRICE)).thenReturn(GLASS_PRICE);
        when(accessoryParametersProvider.getAccessoryPrice(AccessoryKey.BACK_PRICE)).thenReturn(BACK_PRICE);
        when(accessoryParametersProvider.getAccessoryPrice(AccessoryKey.BADGES_SPACE_CM)).thenReturn(BADGES_SPACE_CM);
        when(accessoryParametersProvider.getAccessoryPrice(AccessoryKey.SINGLE_BADGE_PRICE)).thenReturn(SINGLE_BADGE_PRICE);
        when(accessoryParametersProvider.getAccessoryPrice(AccessoryKey.HOOK_PRICE)).thenReturn(HOOK_PRICE);

        frameCalculator = new FrameCalculator(profilePriceProvider, accessoryParametersProvider);
    }

    @Test
    public void frame_price_is_correct() throws CalculatorException {
        Frame frame = new Frame(frameString);

        double testPrice = frameCalculator.getPrice(frame);

        assertEquals(this.price, testPrice);
    }

    @Parameterized.Parameters
    public static Collection<TestParameter> getParameters() {
        Collection<TestParameter> params = new LinkedList<>();

        double framePrice = round(LESS_THAN_METER_PRICE * 2 * (0.1 + 0.15) + ADDON_PRICE);
        double glassPrice = round(GLASS_PRICE * 0.1 * 0.15);
        double backPrice = round(BACK_PRICE * 0.1 * 0.15);
        double badgesPrice = round(SINGLE_BADGE_PRICE * 100 / BADGES_SPACE_CM * 2 * (0.1 + 0.15));
        double hookPrice = round(HOOK_PRICE);

        params.add(new TestParameter("P10 10x15", framePrice));

        params.add(new TestParameter("P10 10x15 S", round(framePrice + glassPrice)));
        params.add(new TestParameter("P10 10x15 T", round(framePrice + backPrice)));
        params.add(new TestParameter("P10 10x15 ST", round(framePrice + glassPrice + backPrice)));

        params.add(new TestParameter("P10 10x15 f", round(framePrice + badgesPrice)));
        params.add(new TestParameter("P10 10x15 STf", round(framePrice + round((glassPrice + backPrice) * 1.5) + badgesPrice)));

        params.add(new TestParameter("P10 10x15 Z", round(framePrice + hookPrice)));

        return params;
    }

    private static double round(double d) {
        return Precision.round(d, 2);
    }

    @Value
    @ToString
    private static class TestParameter {
        private String frameString;
        private double price;
    }

}
