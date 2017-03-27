package pl.krych14m.ramki.wycennikram.ramki.calculators;

import org.apache.commons.math3.util.Precision;

import pl.krych14m.ramki.wycennikram.api.calculators.Calculator;
import pl.krych14m.ramki.wycennikram.api.calculators.CalculatorException;
import pl.krych14m.ramki.wycennikram.api.products.Product;
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

    public FrameCalculator(PureFrameCalculator pureFrameCalculator, GlassCalculator glassCalculator, BackCalculator backCalculator, HookCalculator hookCalculator, BadgesCalculator badgesCalculator) {
        this.pureFrameCalculator = pureFrameCalculator;
        this.glassCalculator = glassCalculator;
        this.backCalculator = backCalculator;
        this.hookCalculator = hookCalculator;
        this.badgesCalculator = badgesCalculator;
    }

    @Override
    public double getPrice(Product product) throws CalculatorException {
        Frame frame = (Frame) product;

        double pureFramePrice = pureFrameCalculator.getPrice(frame);

        double glassAndBackPrice = 0;
        double badgesPrice = 0;
        double hookPrice = 0;

        if (frame.isGlass()) {
            Glass glass = new Glass(frame.getX(), frame.getY());
            glassAndBackPrice += glassCalculator.getPrice(glass);
        }
        if (frame.isBack()) {
            Back back = new Back(frame.getX(), frame.getY());
            glassAndBackPrice += backCalculator.getPrice(back);
        }
        if (frame.isBadges() && (frame.isGlass() || frame.isBack())) {
            glassAndBackPrice = Precision.round(1.5 * glassAndBackPrice, 2);
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
        glassAndBackPrice = Precision.round(glassAndBackPrice, 2);
        hookPrice = Precision.round(hookPrice, 2);
        badgesPrice = Precision.round(badgesPrice, 2);

        double price = pureFramePrice + glassAndBackPrice + hookPrice + badgesPrice;
        return Precision.round(price, 2);
    }

    @Override
    public boolean isProductSupported(Product product) {
        return product.getClass().equals(Frame.class);
    }

}
