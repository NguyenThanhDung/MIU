package lesson5.lecture.intfaces2;

public interface Polygon extends ClosedCurve {
    @Override
    default double computePerimeter() {
        double perimeter = 0;
        for (double length : getLengths())
            perimeter += length;
        return perimeter;
    }

    double[] getLengths();
}
