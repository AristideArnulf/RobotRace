varying vec3 normal;
varying vec3 pos;

vec4 perFragment(vec3 pos, vec3 normal, gl_LightSourceParameters lsp, gl_MaterialParameters mp) {
    vec4 final  = vec4(0,0,0,1);		
    vec4 lspPos = lsp.position;
	vec3 lspPosXYZ = lspPos.xyz;
	float lspPosW = lspPos.w;
	vec3 xyzDivideW = lspPosXYZ/lspPosW;
	vec3 minusPos = xyzDivideW - pos;
    vec3 light = normalize(minusPos);
	vec4 mpDiff = mp.diffuse;
	vec4 lspDiff = lsp.diffuse;
	float max1 = max(dot(normal,light),0.0);
    final = final + mpDiff*lspDiff*max1;
    
    vec3 eyePosition = normalize(-pos);
	vec3 normMinusLight = normal-light;
    vec3 lightD = 2.0*dot(normal,light)*normal-light;
	float max2 = max(dot(lightD,eyePosition), 0.0);
	vec4 mpSpec = mp.specular;
	vec4 lspSpec = lsp.specular;
    final += mpSpec*lspSpec*pow(max2,mp.shininess);
    return final;
}

void main() {
    gl_LightSourceParameters lsp = gl_LightSource[0];
    gl_MaterialParameters mp = gl_FrontMaterial;
    gl_FragColor = perFragment(pos,normal,lsp,mp);
}