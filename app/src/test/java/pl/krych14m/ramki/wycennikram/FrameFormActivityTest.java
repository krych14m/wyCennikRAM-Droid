package pl.krych14m.ramki.wycennikram;

import org.apache.commons.math3.util.Precision;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowAlertDialog;

import pl.krych14m.ramki.wycennikram.api.calculators.CalculatorException;
import pl.krych14m.ramki.wycennikram.ramki.products.Frame;

import static junit.framework.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
public class FrameFormActivityTest {

    @Test
    public void frame_price_calculation_test() throws CalculatorException {
        FrameFormActivity_ activity = Robolectric.setupActivity(FrameFormActivity_.class);
        activity.findViewById(R.id.buttonClearForm).performClick();
        activity.editProfile.setText("10");
        activity.editX.setText("20");
        activity.editY.setText("30");
        activity.radioWornOut.setChecked(true);
        activity.switchBack.setChecked(true);
        activity.switchGlass.setChecked(true);
        activity.switchHook.setChecked(true);

        String frameString = "P10 20x30 P STZf";
        double expectedPrice = activity.calculator.getPrice(new Frame(frameString));
        double expectedPriceWithTax = Precision.round(expectedPrice * 1.23, 2);
        String expectedDialogMessage = activity.generatePriceDialogMessage(frameString, expectedPrice, expectedPriceWithTax);

        activity.findViewById(R.id.buttonAction).performClick();

        ShadowAlertDialog dialog = shadowOf(ShadowAlertDialog.getLatestAlertDialog());
        String dialogMessage = dialog.getMessage().toString();

        assertEquals(expectedDialogMessage, dialogMessage);
    }

}
