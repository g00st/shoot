uniform sampler2D tex;
uniform vec2 newsize;
uniform vec2 oldsize;

void main() {
    vec2 scale = newsize / oldsize;
    vec2 coord = floor(gl_FragCoord.xy / scale);
    vec2 centerCord = (coord + 0.5) / oldsize;
    gl_FragColor = texture2D(tex, centerCord,0.0);

}