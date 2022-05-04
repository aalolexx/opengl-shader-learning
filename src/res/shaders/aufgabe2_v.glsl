#version 330
layout(location=0) in vec2 eckenAusJava;

out vec2 pixelPos;

// SRC: https://thebookofshaders.com/08/
mat2 rotate2d(float _angle){
    return mat2(cos(_angle),-sin(_angle),
    sin(_angle),cos(_angle));
}

void main() {
    //hier kann Transformation erfolgen
    vec2 newPos = rotate2d(20) * eckenAusJava;
    pixelPos = newPos;
    gl_Position = vec4(newPos, 0.0, 1.0);
}