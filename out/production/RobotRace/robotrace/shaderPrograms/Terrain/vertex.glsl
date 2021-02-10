#version 120

varying vec4 pos;
uniform sampler2D heightmap;

float mappedHeight(float x, float y) {
    vec4 color = texture2D(heightmap, gl_TexCoord[0].st); //get color on heightmap. Assumed to be in grayscale, so only using 1 channel
    float height = 2.6 * color.r - 1.2; //re-scale colors to height and shift closer to 0.

    return height;
}

void main()
{
    gl_TexCoord[0] = gl_MultiTexCoord0;
    float z = mappedHeight(gl_Vertex.x, gl_Vertex.y);
    pos = vec4(gl_Vertex.x, gl_Vertex.y, z, 1);
    gl_Position = gl_ModelViewProjectionMatrix * pos;
    gl_FrontColor = gl_Color;
}