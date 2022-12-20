package lesson5.lecture.intfaces2;

public class Ellipse implements ClosedCurve {
    private double semiaxis;
    private double elateral;

    public Ellipse(double semiaxis, double elateral) {
        this.semiaxis = semiaxis;
        this.elateral = elateral;
    }

    public double getSemiaxis() {
        return semiaxis;
    }

    public void setSemiaxis(double semiaxis) {
        this.semiaxis = semiaxis;
    }

    public double getElateral() {
        return elateral;
    }

    public void setElateral(double elateral){
        this.elateral = elateral;
    }

    @Override
    public double computePerimeter() {
        return 4 * semiaxis * elateral;
    }
}
