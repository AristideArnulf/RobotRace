varying float hack;

void main()
{
    gl_FragColor = gl_Color + vec4(hack) * 0; //see vertex shader
}
