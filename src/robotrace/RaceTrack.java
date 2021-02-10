package robotrace;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

import static com.jogamp.opengl.GL2.*;

import com.jogamp.opengl.util.texture.Texture;

/**
 * Implementation of a race track that is made from Bezier segments.
 */
abstract class RaceTrack {

    /**
     * The width of one lane. The total width of the track is 4 * laneWidth.
     */
    private final static float laneWidth = 1.22f;

    /**
     * Constructor for the default track.
     */
    public RaceTrack() {
    }

    /**
     * Draws this track, based on the control points.
     */
    public void draw(GL2 gl, GLU glu, GLUT glut, Texture top, Texture sides) {
        float to = 1f;
        float plus = 2;
        top.enable(gl);
        top.bind(gl);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        int x = 0;
        while (x < 4) {
            gl.glBegin(GL_QUAD_STRIP);
            for (float j = 0; j <= to; j += 0.005f) {
                Vector lanePoint = getLanePoint(x, j);
                Vector laneTangent = getLaneTangent(x, j);
                Vector normal = laneTangent.cross(new Vector(0, 0, 1)).normalized();
                Vector lanePointInner = lanePoint.subtract(normal.scale(laneWidth / 2));
                Vector lanePointOuter = lanePoint.add(normal.scale(laneWidth + (laneWidth / 2)));
                gl.glTexCoord2d(x / 4.0, j / to * 50);
                gl.glVertex3d(lanePointInner.x, lanePointInner.y, lanePointInner.z);
                gl.glTexCoord2d((x + plus) / 4.0, j / to * 50);
                gl.glVertex3d(lanePointOuter.x, lanePointOuter.y, lanePointOuter.z);
            }
            gl.glEnd();
            x += plus;
        }

        top.disable(gl);

        sides.enable(gl);
        sides.bind(gl);
        gl.glBegin(GL_TRIANGLE_STRIP);
        for (float y = to; y >= 0; y -= 0.005f) {
            Vector lanePoint = getLanePoint(0, y);
            Vector laneTangent = getLaneTangent(0, y);
            Vector normal = laneTangent.cross(new Vector(0, 0, 1)).normalized();
            Vector lanePointInner = lanePoint.subtract(normal.scale(laneWidth / 2));
            float num = y * 20 % 1;
            gl.glTexCoord2d(num, 0);
            gl.glVertex3d(lanePointInner.x, lanePointInner.y, lanePointInner.z);
            gl.glTexCoord2d(num, 1);
            gl.glVertex3d(lanePointInner.x, lanePointInner.y, lanePointInner.z - 2);
        }
        gl.glEnd();
        gl.glBegin(GL_TRIANGLE_STRIP);
        for (float z = 0; z <= to; z += 0.005f) {
            Vector lanePoint = getLanePoint(3, z);
            Vector laneTangent = getLaneTangent(3, z);
            Vector normal = laneTangent.cross(new Vector(0, 0, 1)).normalized();
            Vector lanePointOuter = lanePoint.add(normal.scale(laneWidth / 2));
            float num = z * 20 % 1;
            gl.glTexCoord2d(num, 0);
            gl.glVertex3d(lanePointOuter.x, lanePointOuter.y, lanePointOuter.z);
            gl.glTexCoord2d(num, 1);
            gl.glVertex3d(lanePointOuter.x, lanePointOuter.y, lanePointOuter.z - 2);
        }
        gl.glEnd();
        sides.disable(gl);
    }

    /**
     * Returns the center of a lane at 0 <= t < 1.
     * Use this method to find the position of a robot on the track.
     */
    public Vector getLanePoint(int lane, double t) {
        Vector laneTangent = getLaneTangent(0, t);
        Vector normal = laneTangent.cross(Vector.Z).normalized();
        Vector lanePoint = getPoint(t).add(normal.scale(laneWidth * lane));
        return lanePoint;

    }

    /**
     * Returns the tangent of a lane at 0 <= t < 1.
     * Use this method to find the orientation of a robot on the track.
     */
    public Vector getLaneTangent(int lane, double t) {
        return getTangent(t);

    }

    // Returns a point on the test track at 0 <= t < 1.
    protected abstract Vector getPoint(double t);

    // Returns a tangent on the test track at 0 <= t < 1.
    protected abstract Vector getTangent(double t);
}
