#version 120
varying vec4 pos;
void main() {
    if (pos.z < 0) {
        gl_FragColor = vec4(.66, 1.38, 2.45, 1);
    } else if (pos.z < 0.5 && pos.z >= 0 ) {
        gl_FragColor = vec4(.98, .94, .68, 1);
    } else {
        gl_FragColor = vec4(.45, .75, .48, 1);
    }
}
