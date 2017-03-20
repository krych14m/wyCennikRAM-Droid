package pl.krych14m.ramki.wycennikram.api.products;

public abstract class TwoDimensionalProduct implements Product {

    private final double x;
    private final double y;

    public TwoDimensionalProduct(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
