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
    public static final double BADGES_SPACE_CM = 100;
    public static final double SINGLE_BADGE_PRICE = 1;
    public static final double HOOK_PRICE = 0.5;

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
        when(accessoryParametersProvider.getAccessoryPrice(AccessoryKey.BADGES_SPACE_CM)).thenReturn(BADGES_SPACE_CM);
        when(accessoryParametersProvider.getAccessoryPrice(AccessoryKey.SINGLE_BADGE_PRICE)).thenReturn(SINGLE_BADGE_PRICE);
        when(accessoryParametersProvider.getAccessoryPrice(AccessoryKey.HOOK_PRICE)).thenReturn(HOOK_PRICE);

        PureFrameCalculator pureFrameCalculator = new PureFrameCalculator(profilePriceProvider);
        GlassCalculator glassCalculator = new GlassCalculator(accessoryParametersProvider);
        BackCalculator backCalculator = new BackCalculator(accessoryParametersProvider);
        HookCalculator hookCalculator = new HookCalculator(accessoryParametersProvider);
        BadgesCalculator badgesCalculator = new BadgesCalculator(accessoryParametersProvider);
        frameCalculator = new FrameCalculator(pureFrameCalculator, glassCalculator, backCalculator, hookCalculator, badgesCalculator);
    }

    @Test
    public void frame_price_is_correct() throws CalculatorException {
        double testPrice = frameCalculator.getPrice(frame);

        assertEquals(this.price, testPrice);
    }

    @Parameterized.Parameters
    public static Collection<Parameter> getParameters() {
        Collection<Parameter> params = new LinkedList<>();

        double framePrice = round(BASE_PRICE);
        double glassPrice = round(GLASS_PRICE * 0.2 * 0.3);
        double backPrice = round(BACK_PRICE * 0.2 * 0.3);
        double badgesPrice = round(SINGLE_BADGE_PRICE * 100 / BADGES_SPACE_CM);
        double hookPrice = round(HOOK_PRICE);

        Frame frame = Frame.builder().profile("10").x(20).y(30).colorType(ColorType.RAW).build();

        params.add(new Parameter(
                frame,
                framePrice));

        params.add(new Parameter(
                frame.toBuilder().glass(true).build(),
                round(framePrice + glassPrice)));
        params.add(new Parameter(
                frame.toBuilder().back(true).build(),
                round(framePrice + backPrice)));
        params.add(new Parameter(
                frame.toBuilder().glass(true).back(true).build(),
                round(framePrice + glassPrice + backPrice)));

        params.add(new Parameter(
                frame.toBuilder().badges(true).build(),
                round(framePrice + badgesPrice)));
        params.add(new Parameter(
                frame.toBuilder().glass(true).back(true).badges(true).build(),
                round(framePrice + round((glassPrice + backPrice) * 1.5) + badgesPrice)));

        params.add(new Parameter(
                frame.toBuilder().hook(true).build(),
                round(framePrice + hookPrice)));

        return params;
    }

    private static double round(double d) {
        return Precision.round(d, 2);
    }

    @Value
    @ToString
    private static class Parameter {
        private Frame frame;
        private double price;
    }

}
