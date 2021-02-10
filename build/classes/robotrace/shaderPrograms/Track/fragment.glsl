#version 120
varying vec3 normal; 
varying vec3 pos; 
uniform sampler2D startingPic;

vec4 phong(vec3 pos, vec3 normal, gl_LightSourceParameters lsp, gl_MaterialParameters mp){
	vec4 final = vec4(0,0,0,1);

	final = final + lsp.ambient; 

	vec3 posLight = lsp.position.xyz - pos;
	float dotposLightNormal = dot(posLight, normal);
	float lenposLightNormal = length(posLight) * length(normal);
	float anglePLN =  dotposLightNormal / lenposLightNormal;
	vec4 matDiff = mp.diffuse;
	vec4 lightDiff = lsp.diffuse;
    vec4 termDiff =  matDiff * lightDiff * max(anglePLN, 0);
	float termDiffX = termDiff.x;
	float termDiffY = termDiff.y;
	float termDiffZ = termDiff.z;
	final = final + vec4(max(0, termDiffX), max(0, termDiffY), max(0, termDiffZ), 1);
	vec4 camPos = vec4(0, 0, 0, 1);
	vec3 camPosMVM = (gl_ModelViewMatrix  * camPos).xyz;
	vec3 vecPoscamPosMVM = camPosMVM - pos;
	vec3 mvmpluslight = vecPoscamPosMVM + posLight;
	vec3 vecHalfway = (mvmpluslight) / length(mvmpluslight);
	float dotVecHalfwayNorm = dot(vecHalfway, normal);
	float lenVecHalfwayNorm = length(vecHalfway)*length(normal);
	float angleHalfNormal = max(dotVecHalfwayNorm/lenVecHalfwayNorm, 0.0);
	vec4 matSpec = mp.specular-0.5;
	vec4 lightSpec = lsp.specular-0.5;
	float angle2 = pow(max(angleHalfNormal,0), mp.shininess);
	vec4 termSpec = matSpec * lightSpec * angle2; 
	final = final + vec4(max(0, termSpec.x), max(0, termSpec.y), max(0, termSpec.z), 1);
	return vec4(max(0, final.x), max(0, final.y), max(0,final.z), 1);
}

void main()
{
    gl_LightSourceParameters lsp = gl_LightSource[0];
    gl_MaterialParameters mp = gl_FrontMaterial;
    vec4 shader = phong(pos,normal,lsp, mp);
    gl_FragColor = texture2D(startingPic, gl_TexCoord[0].xy) + min(shader, 0.2);
}
