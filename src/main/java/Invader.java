import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

class Invader {
    PImage img;
    PVector pos;
    int size;
    boolean destroyed = false;

    Invader(PImage img, PVector pos, int size) {
        this.img = img;
        this.pos = pos;
        this.size = size;
    }

    void move() {
        pos.y += size *0.1; // adjust this value to change the speed of the invaders
    }

    void draw(PApplet p) {
        if (!destroyed) {
            p.image(img, pos.x, pos.y, size, size);
        }
    }

    boolean isClicked(int mouseX, int mouseY) {
        return mouseX >= pos.x && mouseX <= pos.x + size && mouseY >= pos.y && mouseY <= pos.y + size;
    }
}