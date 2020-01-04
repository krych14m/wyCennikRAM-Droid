package pl.krych14m.ramki.wycennikram.ramki.calculators;

import org.apache.commons.math3.util.Precision;

import pl.krych14m.ramki.wycennikram.api.calculators.Calculator;
import pl.krych14m.ramki.wycennikram.api.calculators.CalculatorException;
import pl.krych14m.ramki.wycennikram.api.products.Product;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.AccessoryParametersProvider;
import pl.krych14m.ramki.wycennikram.ramki.priceproviders.ProfilePriceProvider;
import pl.krych14m.ramki.wycennikram.ramki.products.Back;
import pl.krych14m.ramki.wycennikram.ramki.products.Badges;
import pl.krych14m.ramki.wycennikram.ramki.products.Frame;
import pl.krych14m.ramki.wycennikram.ramki.products.Glass;
import pl.krych14m.ramki.wycennikram.ramki.products.Hook;

public class FrameCalculator implements Calculator {

    private PureFrameCalculator pureFrameCalculator;
    private GlassCalculator glassCalculator;
    private BackCalculator backCalculator;
    private HookCalculator hookCalculator;
    private BadgesCalculator badgesCalculator;

    public FrameCalculator(ProfilePriceProvider profilePriceProvider, AccessoryParametersProvider accessoryParametersProvider) {
        this.pureFrameCalculator = new PureFrameCalculator(profilePriceProvider);
        this.glassCalculator = new GlassCalculator(accessoryParametersProvider);
        this.backCalculator = new BackCalculator(accessoryParametersProvider);
        this.hookCalculator = new HookCalculator(accessoryParametersProvider);
        this.badgesCalculator = new BadgesCalculator(accessoryParametersProvider);
    }

    @Override
    public double getPrice(Product product) throws CalculatorException {
        Frame frame = (Frame) product;

        double pureFramePrice = pureFrameCalculator.getPrice(frame.getPureFrame());

        double glassPrice = 0;
        double backPrice = 0;
        double badgesPrice = 0;
        double hookPrice = 0;

        if (frame.isGlass()) {
            Glass glass = new Glass(frame.getX(), frame.getY());
            glassPrice = glassCalculator.getPrice(glass);
        }
        if (frame.isBack()) {
            Back back = new Back(frame.getX(), frame.getY());
            backPrice = backCalculator.getPrice(back);
        }
        if (frame.isBadges() && (frame.isGlass() || frame.isBack())) {
            glassPrice *= 1.5;
            backPrice *= 1.5;
        }
        if (frame.isBadges()) {
            Badges badges = new Badges(frame.getX(), frame.getY());
            badgesPrice = badgesCalculator.getPrice(badges);
        }
        if (frame.isHook()) {
            Hook hook = new Hook();
            hookPrice = hookCalculator.getPrice(hook);
        }

        pureFramePrice = Precision.round(pureFramePrice, 2);
        glassPrice = Precision.round(glassPrice, 2);
        backPrice = Precision.round(backPrice, 2);
        hookPrice = Precision.round(hookPrice, 2);
        badgesPrice = Precision.round(badgesPrice, 2);

        double price = pureFramePrice + glassPrice + backPrice + hookPrice + badgesPrice;
        return Precision.round(price, 2);
    }

    @Override
    public boolean isProductSupported(Product product) {
        return product.getClass().equals(Frame.class);
    }

}
