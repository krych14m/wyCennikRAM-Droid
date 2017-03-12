package pl.krych14m.ramki.wycennikram.products;

public abstract class ArealProduct implements Product {

    private final double x;
    private final double y;

    public ArealProduct(double x, double y) {
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
