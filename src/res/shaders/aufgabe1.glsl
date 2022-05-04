#version 330
//globale Ausgabe unseres Programms (out)
//Typ ist vec3 für RGB (jeweils 0.0 bis 1.0)
out vec3 pixelFarbe; //Name beliebig

mat2 rotate2d(float _angle){
    return mat2(cos(_angle),-sin(_angle), sin(_angle), cos(_angle));
}

void main() {
    pixelFarbe = vec3(1, 1, 1);

    if (gl_FragCoord.x > 100 && gl_FragCoord.x < 500
        && gl_FragCoord.y > 100 && gl_FragCoord.y < 200) {
        pixelFarbe = vec3(1.0, 0.0, 0.0);
    }

    if (distance(gl_FragCoord.xy, vec2(300.0, 300.0)) < 100) {
        pixelFarbe = vec3(0, 1.0, 0.0);
    }
}