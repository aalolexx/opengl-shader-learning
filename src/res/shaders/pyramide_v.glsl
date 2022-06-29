#version 330
layout(location=0) in vec3 pointsFromJava;
layout(location=1) in vec3 normalsFromJava;

uniform mat4 transformMatrix;
out vec3 faceNormal;
out vec4 currentPos;

void main() {
    // Apply Transofmation Matrix
   vec4 finalPos = transformMatrix * vec4(pointsFromJava, 1.0);
   vec4 finalNormal = transformMatrix * vec4(normalsFromJava, 1.0);

    // Out color for fragment shader
    faceNormal = vec3(normalize(finalNormal));

    // Out Position
    gl_Position = vec4(finalPos);
    currentPos = finalPos;
}