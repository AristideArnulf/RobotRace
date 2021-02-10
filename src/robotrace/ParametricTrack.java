
package robotrace;

/**
 * Implementation of RaceTrack, creating a track from a parametric formula
 */
public class ParametricTrack extends RaceTrack {

    @Override
    protected Vector getPoint(double t) {
        double x = 10 * Math.cos(2 * Math.PI * t);
        double y = 14 * Math.sin(2 * Math.PI * t);
        return new Vector(x, y, 1);

    }

    @Override
    protected Vector getTangent(double t) {
        double x = 20 * Math.PI * -Math.sin(2 * Math.PI * t);
        double y = 28 * Math.PI * Math.cos(2 * Math.PI * t);
        return new Vector(x, y, 0).normalized();
    }

}
