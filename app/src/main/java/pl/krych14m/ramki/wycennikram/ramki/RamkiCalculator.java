package pl.krych14m.ramki.wycennikram.ramki;

import android.content.Context;

import org.androidannotations.annotations.EBean;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.krych14m.ramki.wycennikram.R;
import pl.krych14m.ramki.wycennikram.api.calculators.Calculator;
import pl.krych14m.ramki.wycennikram.api.calculators.CalculatorException;
import pl.krych14m.ramki.wycennikram.api.calculators.MultiProductCalculator;
import pl.krych14m.ramki.wycennikram.api.products.Product;
import pl.krych14m.ramki.wycennikram.ramki.calculators.FrameCalculator;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryParametersProvider;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryParametersProviderImpl;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.ProfilePriceProvider;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.ProfilePriceProviderImpl;

@EBean(scope = EBean.Scope.Singleton)
public class RamkiCalculator implements Calculator {

    private final MultiProductCalculator multiCalculator;
    private final Context context;

    public RamkiCalculator(Context context) {
        this.context = context;
        ProfilePriceProvider profilePriceProvider = prepareProfilePriceProvider();
        AccessoryParametersProvider accessoryParametersProvider = prepareAccessoryParametersProvider();
        List<Calculator> calculators = prepareCalculators(profilePriceProvider, accessoryParametersProvider);
        this.multiCalculator = new MultiProductCalculator(calculators);
    }

    @Override
    public double getPrice(Product product) throws CalculatorException {
        return getPrice(product);
    }

    @Override
    public boolean isProductSupported(Product product) {
        return multiCalculator.isProductSupported(product);
    }

    private List<Calculator> prepareCalculators(ProfilePriceProvider profilePriceProvider, AccessoryParametersProvider accessoryParametersProvider) {
        if (profilePriceProvider == null || accessoryParametersProvider == null) {
            //TODO info box
            //TODO app terminate
            return Collections.emptyList();
        }
        List<Calculator> calculators = new ArrayList<>();
        calculators.add(new FrameCalculator(profilePriceProvider, accessoryParametersProvider));
        return calculators;
    }

    private ProfilePriceProvider prepareProfilePriceProvider() {
        InputStream inputStream = context.getResources().openRawResource(R.raw.cennik_ramek);
        try {
            return new ProfilePriceProviderImpl(inputStream);
        } catch (IOException e) {
            return null;
        }
    }

    private AccessoryParametersProvider prepareAccessoryParametersProvider() {
        InputStream inputStream = context.getResources().openRawResource(R.raw.cennik_akcesoriow);
        try {
            return new AccessoryParametersProviderImpl(inputStream);
        } catch (IOException e) {
            return null;
        }
    }
}
