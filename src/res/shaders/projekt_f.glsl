#version 330

out vec3 color;
in vec3 faceColor;

void main() {
    color = faceColor;
}