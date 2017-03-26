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
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryParametersProvider;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.ProfilePriceImpl;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.ProfilePriceProvider;
import pl.krych14m.ramki.wycennikram.ramki.products.ColorType;
import pl.krych14m.ramki.wycennikram.ramki.products.Frame;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class FrameCalculatorTest {

    public static final double STRIP_PRICE = PureFrameCalculatorTest.STRIP_PRICE;
    public static final double BASE_PRICE = PureFrameCalculatorTest.BASE_PRICE;
    public static final double GLASS_PRICE = 30;
    public static final double BACK_PRICE = 10;

    private final Frame frame;
    private final double price;

    private static FrameCalculator frameCalculator;

    public FrameCalculatorTest(Parameter parameter) {
        this.frame = parameter.getFrame();
        this.price = parameter.getPrice();
    }

    @BeforeClass
    public static void before() {
        ProfilePriceProvider profilePriceProvider = mock(ProfilePriceProvider.class);
        when(profilePriceProvider.getProfilePrice("10")).thenReturn(new ProfilePriceImpl(STRIP_PRICE));

        AccessoryParametersProvider accessoryParametersProvider = mock(AccessoryParametersProvider.class);
        when(accessoryParametersProvider.getAccessoryPrice(AccessoryKey.GLASS_PRICE)).thenReturn(GLASS_PRICE);
        when(accessoryParametersProvider.getAccessoryPrice(AccessoryKey.BACK_PRICE)).thenReturn(BACK_PRICE);

        PureFrameCalculator pureFrameCalculator = new PureFrameCalculator(profilePriceProvider);
        GlassCalculator glassCalculator = new GlassCalculator(accessoryParametersProvider);
        BackCalculator backCalculator = new BackCalculator(accessoryParametersProvider);
        HookCalculator hookCalculator = new HookCalculator(accessoryParametersProvider);
        BadgesCalculator badgesCalculator = new BadgesCalculator(accessoryParametersProvider);
        frameCalculator = new FrameCalculator(pureFrameCalculator, glassCalculator, backCalculator, hookCalculator, badgesCalculator);
    }

    @Test
    public void frame_price_is_correct() throws CalculatorException {
        double expectedPrice = frameCalculator.getPrice(frame);

        assertEquals(expectedPrice, this.price);
    }

    @Parameterized.Parameters
    public static Collection<Parameter> getParameters() {
        Collection<Parameter> params = new LinkedList<>();

        double framePrice = BASE_PRICE;
        double glassPrice = Precision.round(GLASS_PRICE * 0.2 * 0.3, 2);
        double backPrice = Precision.round(BACK_PRICE * 0.2 * 0.3, 2);

        Frame frame = Frame.builder().profile("10").x(20).y(30).colorType(ColorType.RAW).build();

        params.add(new Parameter(frame, framePrice));
        params.add(new Parameter(frame.toBuilder().glass(true).build(), framePrice + glassPrice));
        params.add(new Parameter(frame.toBuilder().back(true).build(), framePrice + backPrice));

        return params;
    }

    @Value
    @ToString
    private static class Parameter {
        private Frame frame;
        private double price;
    }

}
