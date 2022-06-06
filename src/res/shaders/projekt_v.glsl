#version 330
layout(location=0) in vec3 pointsFromJava;
layout(location=1) in vec3 colorsFromJava;

uniform mat4 transformMatrix;
out vec3 faceColor;

void main() {
    // Apply Transofmation Matrix
    vec4 finalPos = transformMatrix * vec4(pointsFromJava, 1.0);

    // Out color for fragment shader
    faceColor = colorsFromJava;

    // Out Position
    gl_Position = vec4(finalPos);
}