package robotrace;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

import static com.jogamp.opengl.GL2.GL_CURRENT_BIT;
import static com.jogamp.opengl.GL2.GL_POLYGON;
import static com.jogamp.opengl.GL2ES3.GL_QUADS;
import static robotrace.ShaderPrograms.*;
import static robotrace.Textures.*;

/**
 * Represents the terrain, to be implemented according to the Assignments.
 */
class Terrain {


    public Terrain() {

    }

    /**
     * Draws the terrain.
     */
    public void draw(GL2 gl, GLU glu, GLUT glut, float tAnim) {
        terrainShader.useProgram(gl);
        heightmap.enable(gl);
        heightmap.bind(gl);
        gl.glColor4d(1, 1, 1, 1);
        drawSquare(256, 40, gl, true);

        waterShader.useProgram(gl);
        waterShader.setUniform(gl, "tAnim", tAnim);
        gl.glPushAttrib(GL_CURRENT_BIT);
        gl.glColor4d(0.8, 0.8, 0.8, 0.5);
        drawSquare(256, 40, gl, false);
        gl.glPopAttrib();
    }

    private void drawSquare(int segments, int side, GL2 gl, boolean texture) {
        double segmentSide = side / (double) segments;
        gl.glBegin(gl.GL_QUADS);
        for (double i = -side / 2.0; i < side / 2.0; i += segmentSide) {
            for (double j = -side / 2.0; j < side / 2.0; j += segmentSide) {
                Vector a = new Vector(i, j, 0);
                Vector b = new Vector(i + segmentSide, j, 0);
                Vector c = new Vector(i + segmentSide, j + segmentSide, 0);
                Vector d = new Vector(i, j + segmentSide, 0);
                if (texture) {
                    gl.glTexCoord2d(a.x() / side + 0.5, a.y() / side + 0.5);
                    gl.glVertex3d(a.x(), a.y(), a.z());
                    gl.glTexCoord2d(b.x() / side + 0.5, b.y() / side + 0.5);
                    gl.glVertex3d(b.x(), b.y(), b.z());
                    gl.glTexCoord2d(c.x() / side + 0.5, c.y() / side + 0.5);
                    gl.glVertex3d(c.x(), c.y(), c.z());
                    gl.glTexCoord2d(d.x() / side + 0.5, d.y() / side + 0.5);
                    gl.glVertex3d(d.x(), d.y(), d.z());
                } else {
                    gl.glVertex3d(a.x(), a.y(), a.z());
                    gl.glVertex3d(b.x(), b.y(), b.z());
                    gl.glVertex3d(c.x(), c.y(), c.z());
                    gl.glVertex3d(d.x(), d.y(), d.z());
                }
            }
        }
        gl.glEnd();
    }
}
