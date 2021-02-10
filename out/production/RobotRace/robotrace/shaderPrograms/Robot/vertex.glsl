// simple vertex shader

varying vec3 normal;
varying vec3 pos;

void main()
{
    vec4 position4 = gl_ModelViewProjectionMatrix * gl_Vertex;
    pos = position4.xyz / position4.w;

    normal = normalize(gl_NormalMatrix*gl_Normal);
    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
}  