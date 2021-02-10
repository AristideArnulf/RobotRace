
package robotrace;

import static java.lang.Math.*;

/**
 * Implementation of RaceTrack, creating a track from control points for a
 * cubic Bezier curve
 */
public class BezierTrack extends RaceTrack {

    private Vector[] controlPoints;


    BezierTrack(Vector[] controlPoints) {
        this.controlPoints = controlPoints;

    }

    @Override
    protected Vector getPoint(double t) {
        t %= 1;
        int segsPerPoint = controlPoints.length / 4;
        int floor = (int) floor(t * segsPerPoint);
        int whichSeg = floor * 4;
        t *= segsPerPoint;
        t %= 1;
        return getCubicBezierPnt(t, controlPoints[whichSeg], controlPoints[whichSeg + 1], controlPoints[whichSeg + 2], controlPoints[whichSeg + 3]);

    }

    @Override
    protected Vector getTangent(double t) {
        t %= 1;
        int segsPerPoint = controlPoints.length / 4;
        int floor = (int) floor(t * segsPerPoint);
        int whichSeg = floor * 4;
        t *= segsPerPoint;
        t %= 1;
        return getCubicBezierTng(t, controlPoints[whichSeg], controlPoints[whichSeg + 1], controlPoints[whichSeg + 2], controlPoints[whichSeg + 3]);

    }

    private Vector getCubicBezierPnt(double t, Vector a, Vector b, Vector c, Vector d) {
        return new Vector((pow((1 - t), 3) * a.x) +
                (3 * t * pow((1 - t), 2) * b.x) +
                (3 * pow(t, 2) * (1 - t) * c.x) +
                (pow(t, 3) * d.x),
                (pow((1 - t), 3) * a.y) +
                        (3 * t * pow((1 - t), 2) * b.y) +
                        (3 * pow(t, 2) * (1 - t) * c.y) +
                        (pow(t, 3) * d.y),
                (pow((1 - t), 3) * a.z) +
                        (3 * t * pow((1 - t), 2) * b.z) +
                        (3 * pow(t, 2) * (1 - t) * c.z) +
                        (pow(t, 3) * d.z));
    }

    Vector getCubicBezierTng(double t, Vector a, Vector b, Vector c, Vector d) {
        return new Vector((3 * pow(1 - t, 2) * (b.x - a.x)) +
                (6 * (1 - t) * t * (c.x - b.x)) +
                (3 * pow(t, 2) * (d.x - c.x)),
                (3 * pow(1 - t, 2) * (b.y - a.y)) +
                        (6 * (1 - t) * t * (c.y - b.y)) +
                        (3 * pow(t, 2) * (d.y - c.y)),
                (3 * pow(1 - t, 2) * (b.z - a.z)) +
                        (6 * (1 - t) * t * (c.z - b.z)) +
                        (3 * pow(t, 2) * (d.z - c.z)));
    }

}
