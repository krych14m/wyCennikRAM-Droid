package pl.krych14m.ramki.wycennikram.ramki.calculators;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import pl.krych14m.ramki.wycennikram.api.calculators.Calculator;
import pl.krych14m.ramki.wycennikram.api.calculators.CalculatorException;
import pl.krych14m.ramki.wycennikram.api.calculators.MultiProductCalculator;
import pl.krych14m.ramki.wycennikram.api.products.Product;

import static junit.framework.Assert.assertEquals;

public class MultiProductCalculatorTest {

    private static final double DOOR_PRICE = 100;
    private static final double WOODEN_DOOR_PRICE = DOOR_PRICE + 300;

    private MultiProductCalculator productCalculator;

    @Before
    public void before() {
        List<Calculator> calculators = Arrays.asList(getDoorCalc(), getWoodenDoorCalc());
        productCalculator = new MultiProductCalculator(calculators);
    }

    @Test
    public void proper_door_price() throws CalculatorException {
        Door door = new Door();

        double price = productCalculator.getPrice(door);

        assertEquals(DOOR_PRICE, price);
    }

    @Test
    public void proper_wooden_door_price() throws CalculatorException {
        Door woodenDoor = new WoodenDoor();

        double price = productCalculator.getPrice(woodenDoor);

        assertEquals(WOODEN_DOOR_PRICE, price);
    }

    private static Calculator getDoorCalc() {
        return new Calculator() {
            @Override
            public double getPrice(Product product) throws CalculatorException {
                return DOOR_PRICE;
            }

            @Override
            public boolean isProductSupported(Product product) {
                return product.getClass().equals(Door.class);
            }
        };
    }

    private static Calculator getWoodenDoorCalc() {
        return new Calculator() {
            @Override
            public double getPrice(Product product) throws CalculatorException {
                return WOODEN_DOOR_PRICE;
            }

            @Override
            public boolean isProductSupported(Product product) {
                return product.getClass().equals(WoodenDoor.class);
            }
        };
    }


    private static class Door implements Product {
        @Override
        public String getName() {
            return null;
        }
    }

    private static class WoodenDoor extends Door {
    }

}
