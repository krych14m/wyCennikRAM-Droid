package pl.krych14m.ramki.wycennikram.ramki;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import pl.krych14m.ramki.wycennikram.api.calculators.Calculator;
import pl.krych14m.ramki.wycennikram.api.calculators.CalculatorException;
import pl.krych14m.ramki.wycennikram.api.calculators.MultiProductCalculator;
import pl.krych14m.ramki.wycennikram.api.products.Product;
import pl.krych14m.ramki.wycennikram.ramki.calculators.FrameCalculator;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryParametersProvider;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.ProfilePriceProvider;

@EBean(scope = EBean.Scope.Singleton)
public class RamkiCalculator implements Calculator {

    private final MultiProductCalculator multiCalculator;

    public RamkiCalculator() {
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
        List<Calculator> calculators = new ArrayList<>();
        calculators.add(new FrameCalculator(profilePriceProvider, accessoryParametersProvider));
        return calculators;
    }

    private ProfilePriceProvider prepareProfilePriceProvider() {
        throw new Error("not yet implemented");
    }

    private AccessoryParametersProvider prepareAccessoryParametersProvider() {
        throw new Error("not yet implemented");
    }
}
