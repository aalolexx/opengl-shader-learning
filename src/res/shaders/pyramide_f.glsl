#version 330

out vec3 color;
in vec3 faceNormal;
in vec4 currentPos;

void main() {
    // TODO get color from java
    color = vec3(0.5, 0.7, 0.1);

    // TODO Make Light Consts editable
    float ambientIntensity = 0.4;
    float diffuseIntensity = 0.4;
    float spectacularIntensity = 0.2;
    float lightSourceIntensity = 3.5;
    vec3 lightPos = vec3(0.5, -0.5, -1);
    float materialHardness = 2;

    float ambientLight;
    float diffuseLight;
    float spectacularLight;
    float totalLight;

    // Calculate required vectors
    vec3 n = faceNormal;
    vec3 l = normalize(lightPos - vec3(currentPos));
    vec3 r = reflect(l, n);
    vec3 v = normalize(vec3(currentPos));

    // Ambient Light
    ambientLight = ambientIntensity;

    // Diffuse Light
    diffuseLight = diffuseIntensity * dot(-l, n);

    // Spectacular LIght
    spectacularLight = spectacularIntensity * (pow(max(dot(r, v), 0), materialHardness));

    // All lights combined
    totalLight = ambientLight + lightSourceIntensity * (diffuseLight + spectacularLight);
    if (totalLight < 0) totalLight = 0;

    color *= totalLight;

    // Show Normals for debugging (NO LIGHTING)
    // color = faceNormal;
    // if (color[0] < 0) color[0] *= -1;
    // if (color[1] < 0) color[1] *= -1;
    // if (color[2] < 0) color[2] *= -1;

}