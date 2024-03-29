#version 330

out vec3 color;
in vec2 pixelPos;
in float redTone;

void main() {
    float greenTone = pixelPos.y;
    float blueTone = pixelPos.x;
    if (pixelPos.y < 0) greenTone *= -1;
    if (pixelPos.x < 0) blueTone *= -1;
    color = vec3(redTone, greenTone, blueTone);
}