// simple vertex shader

varying vec3 normal;
varying vec3 pos;

void main()
{
	vec4 mvmTimesV = (gl_ModelViewMatrix*gl_Vertex);
	vec3 mvmTimesVXYZ = mvmTimesV.xyz ;
	float mvmTimesVW = mvmTimesV.w;
	pos = mvmTimesVXYZ/mvmTimesVW;
	normal = normalize(gl_NormalMatrix*gl_Normal);
	gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;

}  