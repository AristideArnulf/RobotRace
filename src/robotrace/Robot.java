package robotrace;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

import static com.jogamp.opengl.GL.*;
import static com.jogamp.opengl.GL2ES3.GL_QUADS;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.*;
import static java.lang.Math.sin;

import java.util.Random;

/**
 * Represents a Robot, to be implemented according to the Assignments.
 */
class Robot {

    /**
     * The position of the robot.
     */
    public Vector position = new Vector(0, 0, 0);

    /**
     * The direction in which the robot is running.
     */
    public Vector direction = new Vector(1, 0, 0);

    public double torsoHeight = 0.9;
    public double limbLength = 0.9;
    public double headSize = 0.6;
    public double armSwing = 1;

    /**
     * The material from which this robot is built.
     */
    private final Material material;

    public float current;
    public float additional;
    public float winning = 0f;

    /**
     * Constructs the robot with initial parameters.
     */
    public Robot(Material material, double torsoHeight, double limbLength, double headSize, double armSwing) {
        this.material = material;
        this.current = new Random().nextFloat() * 1.5f * 0.4f;
        this.additional = new Random().nextFloat() * 1.5f * 0.005f;

        this.torsoHeight = torsoHeight;
        this.limbLength = limbLength;
        this.armSwing = armSwing;
        this.headSize = headSize;
    }

    private void updateCurrentWinner(float a) {
        double curr = Math.cos(a * current);
        if (curr > 0.512) {
            winning += additional;
        }
    }

    /**
     * Draws this robot.
     */
    public void draw(GL2 gl, GLU glu, GLUT glut, float tAnim) {
        gl.glMaterialfv(GL_FRONT, GL_DIFFUSE, this.material.diffuse, 0);
        gl.glMaterialfv(GL_FRONT, GL_SPECULAR, this.material.specular, 0);
        gl.glMaterialf(GL_FRONT, GL_SHININESS, this.material.shininess);
        gl.glPushMatrix();
        gl.glTranslated(position.x, position.y, position.z);
        direction = direction.normalized();
        Vector racetrackUp = new Vector(1, 0, 0).cross(direction);
        double where = Math.acos(new Vector(1, 0, 0).dot(direction)) / Math.PI * 180;
        gl.glRotated(where, racetrackUp.x, racetrackUp.y, racetrackUp.z);
        gl.glTranslated(0, 0, limbLength * 9 / 10 + torsoHeight);
        makeHead(gl, glu, glut, tAnim);
        gl.glPopMatrix();
        updateCurrentWinner(tAnim + winning);
    }

    public void makeHead(GL2 gl, GLU glu, GLUT glut, float tAnim) {
        gl.glPushMatrix();
        gl.glTranslated(0, 0, headSize / 2);
        gl.glScaled(headSize, headSize, headSize);
        glut.glutSolidCube(1);
        gl.glPopMatrix();
        makeBody(gl, glu, glut, tAnim);
    }

    public void makeBody(GL2 gl, GLU glu, GLUT glut, float tAnim) {
        gl.glPushMatrix();
        gl.glTranslated(0, 0, -torsoHeight / 2);
        gl.glScaled(torsoHeight / 2, torsoHeight / 4, torsoHeight);
        glut.glutSolidCube(1);
        gl.glPopMatrix();
        makeArmRight(gl, glu, glut, tAnim);
        makeArmLeft(gl, glu, glut, tAnim);
    }

    public void makeArmRight(GL2 gl, GLU glu, GLUT glut, float tAnim) {
        gl.glPushMatrix();
        double anim1 = 19 * sin(4 * tAnim) * armSwing;
        gl.glRotated(anim1, 1, 0, 0);
        gl.glTranslated(torsoHeight / 4, 0, -limbLength / 2);
        gl.glScaled(limbLength / 5, limbLength / 5, limbLength);
        glut.glutSolidCube(1);
        gl.glPopMatrix();
        makeLegRight(gl, glu, glut, tAnim);
    }

    public void makeArmLeft(GL2 gl, GLU glu, GLUT glut, float tAnim) {
        gl.glPushMatrix();
        double anim1 = -19 * sin(4 * tAnim) * armSwing;
        gl.glRotated(anim1, 1, 0, 0);
        gl.glTranslated(-torsoHeight / 4, 0, -limbLength / 2);
        gl.glScaled(limbLength / 5, limbLength / 5, limbLength);
        glut.glutSolidCube(1);
        gl.glPopMatrix();
        makeLegLeft(gl, glu, glut, tAnim);
    }

    public void makeLegRight(GL2 gl, GLU glu, GLUT glut, float tAnim) {
        gl.glPushMatrix();
        double anim1 = (-8 * sin(4 * tAnim) + 4);
        gl.glTranslated(-torsoHeight / 4 + limbLength / 10, 0, -torsoHeight + limbLength / 10);
        gl.glRotated(anim1, 1, 0, 0);
        gl.glTranslated(0, 0, -limbLength / 2);
        gl.glScaled(limbLength / 5, limbLength / 5, limbLength);
        glut.glutSolidCube(1);
        gl.glPopMatrix();
    }

    public void makeLegLeft(GL2 gl, GLU glu, GLUT glut, float tAnim) {
        gl.glPushMatrix();
        double anim1 = (8 * sin(4 * tAnim) + 4);
        gl.glTranslated(torsoHeight / 4 - limbLength / 10, 0, -torsoHeight + limbLength / 10);
        gl.glRotated(anim1, 1, 0, 0);
        gl.glTranslated(0, 0, -limbLength / 2);
        gl.glScaled(limbLength / 5, limbLength / 5, limbLength);
        glut.glutSolidCube(1);
        gl.glPopMatrix();
    }


}
