package pl.krych14m.ramki.wycennikram.ramki;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import pl.krych14m.ramki.wycennikram.api.calculators.Calculator;
import pl.krych14m.ramki.wycennikram.api.calculators.CalculatorException;
import pl.krych14m.ramki.wycennikram.api.calculators.MultiProductCalculator;
import pl.krych14m.ramki.wycennikram.api.products.Product;

@EBean(scope = EBean.Scope.Singleton)
public class RamkiCalculator implements Calculator {

	private final MultiProductCalculator multiCalculator;

	public RamkiCalculator() {
		List<Calculator> calculators = new ArrayList<>();
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
}
