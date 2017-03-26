package pl.krych14m.ramki.wycennikram.ramki.calculators;

import org.apache.commons.math3.util.Precision;
import org.junit.Before;
import org.junit.Test;

import pl.krych14m.ramki.wycennikram.api.calculators.CalculatorException;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.ProfilePriceImpl;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.ProfilePriceProvider;
import pl.krych14m.ramki.wycennikram.ramki.products.ColorType;
import pl.krych14m.ramki.wycennikram.ramki.products.PureFrame;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PureFrameCalculatorTest {

    private static final double STRIP_PRICE = 7.61;
    private static final double LESS_THAN_METER_PRICE = STRIP_PRICE;
    private static final double BASE_PRICE = Precision.round(7.61 / 0.7, 2);
    private static final double ADDON_PRICE = BASE_PRICE - LESS_THAN_METER_PRICE;

    private PureFrameCalculator pureFrameCalculator;

    @Before
    public void before() {
        ProfilePriceProvider profilePriceProvider = mock(ProfilePriceProvider.class);
        when(profilePriceProvider.getProfilePrice("10")).thenReturn(new ProfilePriceImpl(LESS_THAN_METER_PRICE));
        this.pureFrameCalculator = new PureFrameCalculator(profilePriceProvider);
    }

    @Test
    public void raw_frame_price_is_correct() throws CalculatorException {
        PureFrame pureFrame = new PureFrame("10", 20, 30, ColorType.RAW);

        double price = pureFrameCalculator.getPrice(pureFrame);

        double expected = Precision.round(BASE_PRICE, 2);
        assertEquals(expected, price);
    }

    @Test
    public void less_than_meter_raw_frame_price_is_correct() throws CalculatorException {
        PureFrame pureFrame = new PureFrame("10", 10, 15, ColorType.RAW);

        double price = pureFrameCalculator.getPrice(pureFrame);

        double expected = Precision.round(0.5 * LESS_THAN_METER_PRICE + ADDON_PRICE, 2);
        assertEquals(expected, price);
    }


    @Test
    public void stain_frame_price_is_correct() throws CalculatorException {
        PureFrame pureFrame = new PureFrame("10", 20, 30, ColorType.STAIN);

        double price = pureFrameCalculator.getPrice(pureFrame);

        double expected = Precision.round(1.5 * BASE_PRICE, 2);
        assertEquals(expected, price);
    }

    @Test
    public void opaque_frame_price_is_correct() throws CalculatorException {
        PureFrame pureFrame = new PureFrame("10", 20, 30, ColorType.OPAQUE);

        double price = pureFrameCalculator.getPrice(pureFrame);

        double expected = Precision.round(1.5 * 1.2 * BASE_PRICE, 2);
        assertEquals(expected, price);
    }

    @Test
    public void worn_out_frame_price_is_correct() throws CalculatorException {
        PureFrame pureFrame = new PureFrame("10", 20, 30, ColorType.WORN_OUT);

        double price = pureFrameCalculator.getPrice(pureFrame);

        double expected = Precision.round(1.5 * 1.4 * BASE_PRICE, 2);
        assertEquals(expected, price);
    }

}
