#version 120
// simple vertex shader

varying vec3 pos;
varying vec3 normal;

void main()
{   
    pos = (gl_ModelViewMatrix *  gl_Vertex).xyz;
    normal = normalize(gl_NormalMatrix * gl_Normal);
    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;      
    gl_FrontColor = gl_Color;
    gl_TexCoord[0] = gl_MultiTexCoord0;
}