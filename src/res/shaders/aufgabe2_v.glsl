#version 330
layout(location=0) in vec2 pointsFromJava;
layout(location=1) in float redTonesFromJava;

out vec2 pixelPos;
out float redTone;

// SRC: https://thebookofshaders.com/08/
mat2 rotate2d(float _angle){
    return mat2(cos(_angle),-sin(_angle),
    sin(_angle),cos(_angle));
}

void main() {
    redTone = redTonesFromJava;
    //Transform here
    // Rotate in Deg
    vec2 newPos = rotate2d(1) * pointsFromJava;
    pixelPos = newPos;
    gl_Position = vec4(newPos, 0.0, 1.0);
}