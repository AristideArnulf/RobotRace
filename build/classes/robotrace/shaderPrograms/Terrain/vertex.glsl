#version 120
// simple vertex shader
varying vec4 pos;

float formulaHeight(float x, float y) {
    return ((0.6 * cos(0.3 * x + 0.2 * y)) + 0.4 * cos(x - 0.5 * y));
}

void main()
{
	float z = formulaHeight(gl_Vertex.x, gl_Vertex.y);
    pos = vec4(gl_Vertex.x, gl_Vertex.y, z, 1);
    gl_Position = gl_ModelViewProjectionMatrix * pos;      
    gl_FrontColor = gl_Color;
}