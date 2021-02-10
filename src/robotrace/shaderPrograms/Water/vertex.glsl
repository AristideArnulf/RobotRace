uniform float tAnim;

//this does nothing
//removing it (or adding #version directives) however,
//will somehow prompt opengl to optimize away tAnim entirely, since it's not in fragment shader
//this obviously breaks everything, so hack stays
varying float hack;

void main()
{
    hack = tAnim;
    double offset = abs(0.05 * sin(0.34 * tAnim + gl_Vertex.x + 0.48) + 0.07 * sin(0.55 * tAnim + gl_Vertex.y + 0.24));
    gl_Position    = gl_ModelViewProjectionMatrix * (gl_Vertex + vec4(0, 0, offset, 0));// model view transform
    gl_FrontColor = gl_Color;
}