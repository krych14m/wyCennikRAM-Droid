package pl.krych14m.ramki.wycennikram;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.math3.util.Precision;

import java.util.Locale;

import pl.krych14m.ramki.wycennikram.ramki.RamkiCalculator;
import pl.krych14m.ramki.wycennikram.ramki.products.ColorType;
import pl.krych14m.ramki.wycennikram.ramki.products.Frame;

@EActivity(R.layout.activity_frame_form)
public class FrameFormActivity extends AppCompatActivity {

    @ViewById
    EditText editProfile;
    @ViewById
    EditText editX;
    @ViewById
    EditText editY;
    @ViewById
    RadioButton radioRaw;
    @ViewById
    RadioButton radioStain;
    @ViewById
    RadioButton radioOpaque;
    @ViewById
    RadioButton radioWornOut;
    @ViewById
    Switch switchGlass;
    @ViewById
    Switch switchBack;
    @ViewById
    Switch switchHook;
    @ViewById
    Switch switchBadges;

    @Bean
    RamkiCalculator calculator;

    @Click(R.id.buttonAction)
    void actionButtonClick() {
        try {
            Frame frame = getFrameData();
            double price = calculator.getPrice(frame);
            double priceWithTax = Precision.round(price * 1.23, 2);
            new AlertDialog.Builder(this)
                    .setMessage(frame.getName() + "\n"
                            + String.format(Locale.getDefault(), "%.2f brutto = %.2f netto", priceWithTax, price))
                    .setCancelable(true)
                    .create()
                    .show();
        } catch (Exception e) {
            new AlertDialog.Builder(this)
                    .setMessage(e.getClass().getSimpleName())
                    .setCancelable(true)
                    .create()
                    .show();
        }
    }

    @Click(R.id.buttonClearForm)
    @UiThread
    void clearButtonClick() {
        editProfile.setText("");
        editX.setText("");
        editY.setText("");
        radioRaw.setChecked(true);
        radioStain.setChecked(false);
        radioOpaque.setChecked(false);
        radioWornOut.setChecked(false);
        switchGlass.setChecked(false);
        switchBack.setChecked(false);
        switchHook.setChecked(false);
        switchBadges.setChecked(false);
    }

    @CheckedChange({R.id.switchGlass, R.id.switchBack})
    void accessoriesSwitchChanged(boolean isChecked) {
        if (isChecked) {
            switchBadges.setChecked(true);
        }
    }

    private Frame getFrameData() {
        ColorType colorType = ColorType.RAW;
        if (bool(radioStain)) colorType = ColorType.STAIN;
        if (bool(radioOpaque)) colorType = ColorType.OPAQUE;
        if (bool(radioWornOut)) colorType = ColorType.WORN_OUT;
        return new Frame(
                str(editProfile),
                num(editX),
                num(editY),
                colorType,
                bool(switchGlass),
                bool(switchBack),
                bool(switchHook),
                bool(switchBadges)
        );
    }

    private static String str(EditText edit) {
        return edit.getText().toString();
    }

    private static double num(EditText edit) {
        return Double.valueOf(edit.getText().toString());
    }

    private static boolean bool(RadioButton radio) {
        return radio.isChecked();
    }

    private static boolean bool(Switch sw) {
        return sw.isChecked();
    }

}
