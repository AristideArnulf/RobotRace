#version 120
varying vec3 normal;
varying vec3 pos;
uniform sampler2D startingPic;

vec4 phong(vec3 posVector, vec3 N){
    //use pre-computed values for (material * light) coloring
    vec4 finalVec = gl_FrontLightModelProduct.sceneColor;

    vec3 posLightVector = normalize(gl_LightSource[0].position.xyz - posVector.xyz);//The vector from pos to the light source

    float cosNormalLight = dot(posLightVector, N);// The cos of angle between the fragment normal and light beam

    //only calculate diffuse and specular reflection if source is actually in front of the surface and light can hit it
    if (cosNormalLight >= 0.0) {
        finalVec += gl_FrontLightProduct[0].diffuse * cosNormalLight;//cos is positive here, so this is always positive

        vec3 posCamVec = normalize(-posVector.xyz);//everything is in eye-space, so camera is at origin
        float cosReflectCamera = dot(posCamVec, normalize(reflect(-posLightVector, N)));//angle between camera direction and specular reflected light

        //don't add specular reflection if the angle is too large (negative cosine)
        if (cosReflectCamera > 0.0) {
            finalVec += gl_FrontLightProduct[0].specular * pow(cosReflectCamera, gl_FrontMaterial.shininess);
        }
    }

    return max(finalVec, 0);
}

void main()
{
    vec4 shader = phong(pos, normal);
    gl_FragColor = texture2D(startingPic, gl_TexCoord[0].xy) + min(shader, 0.2);
}
