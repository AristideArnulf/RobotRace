#version 120

varying vec4 pos;
uniform sampler2D tex;
void main() {
    if (pos.z < 0) {
        gl_FragColor = vec4(.66, 1.38, 2.45, 1);//water
    } else if (pos.z < 0.3) {
        gl_FragColor = vec4(.98, .94, .68, 1);//sand
    } else if (pos.z < 0.7) {
        gl_FragColor = vec4(.45, .75, .48, 1);//grass
    } else {
        gl_FragColor = vec4(.85, .85, .95, 1);//snow
    }
}
