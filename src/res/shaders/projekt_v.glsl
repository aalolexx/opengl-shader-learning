#version 330
layout(location=0) in vec2 pointsFromJava;
layout(location=1) in float redTonesFromJava;

out vec2 pixelPos;
out float redTone;

uniform mat4 transformMatrix;

void main() {
    redTone = redTonesFromJava;
    //Transform here
    vec4 finalPos = transformMatrix * vec4(pointsFromJava, 0.0, 1.0);
    pixelPos = vec2(finalPos);
    gl_Position = vec4(finalPos);
}