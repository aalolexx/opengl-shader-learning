#version 330

out vec3 color;
in vec3 faceColor;

void main() {
    color = faceColor;
    // Make vector positiv to better visualize normal vector
    if (color[0] < 0) color[0] *= -1;
    if (color[1] < 0) color[1] *= -1;
    if (color[2] < 0) color[2] *= -1;
}