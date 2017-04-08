package pl.krych14m.ramki.wycennikram.ramki.calculators;

import pl.krych14m.ramki.wycennikram.api.calculators.Calculator;
import pl.krych14m.ramki.wycennikram.api.calculators.CalculatorException;
import pl.krych14m.ramki.wycennikram.api.products.Product;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryKey;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryParameterNotFoundException;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryParametersProvider;
import pl.krych14m.ramki.wycennikram.ramki.products.Hook;

public class HookCalculator implements Calculator {

    private AccessoryParametersProvider accessoryParametersProvider;

    public HookCalculator(AccessoryParametersProvider accessoryParametersProvider) {
        this.accessoryParametersProvider = accessoryParametersProvider;
    }

    @Override
    public double getPrice(Product product) throws CalculatorException {
        try {
            return accessoryParametersProvider.getAccessoryPrice(AccessoryKey.HOOK_PRICE);
        } catch (AccessoryParameterNotFoundException e) {
            throw new CalculatorException("hook price error", e);
        }
    }

    @Override
    public boolean isProductSupported(Product product) {
        return Hook.class.equals(product.getClass());
    }
}
