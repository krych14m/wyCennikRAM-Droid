package pl.krych14m.ramki.wycennikram.products;

public class Glass implements Product {

    private final double x;
    private final double y;

    public Glass(double x, double y) {
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
