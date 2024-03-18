import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.opengl.PShader;
import controlP5.*;

import java.util.ArrayList;


public class MySketch extends PApplet {
	ControlP5 cp5;
	float  time = millis();
	Randomset rs;
	PShader nearestNeighbor;
	ArrayList<Invader> invaders = new ArrayList<>();
	int score = 0;
	public void setup() {
		this.getSurface().setResizable(false);
		cp5 = new ControlP5(this);
		 nearestNeighbor = loadShader("nearest.glsl");
		 setColors(5);

	}
	public void setColors(int c){
		rs = new Randomset();
		rs.addcolor(color(0, 0, 0,255), 0.5f);
		for (int i = 0; i < c; i++){
			rs.addcolor(color(random(0,255), random(0,255), random(0,255)), 0.5f/c);
		}}

	public void settings() {
		size(500, 500,P2D);
	}

	public void frameResized(int w, int h) {

	}

	public void draw() {
		background(0);
		if(millis() - time > 300) {
			setColors(5);
			var x= generateInvader(20, 20, rs);

			int scale = (int)random(1, 5);;
			x = resizenearesNeigbor(x, scale);
			invaders.add(new Invader(x, new PVector(random(width), 0), 20*scale));

			time = millis();
		}

		for (Invader invader : invaders) {
			invader.move();
			invader.draw(this);
			if (invader.isClicked(mouseX, mouseY) && mousePressed) {
				invader.destroyed = true;
				score++;
			}
		}

		invaders.removeIf(invader -> invader.destroyed || invader.pos.y > height);

		text("Score: " + score, 10, 20); // display the score
	}


	private PImage generateInvader(int x , int y , Randomset rs){
			PImage img = createImage(x, y, ARGB);
			img.loadPixels();
			for (int i = 0; i < y; i++){
				for (int j = 0; j < x/2; j++){
					int color = rs.getrandomcolor();
					img.pixels[i*x+j] = color;
					img.pixels[i*x+x-j-1] = color;
				}
				if (x%2 != 0){
					img.pixels[i*x+x/2] = rs.getrandomcolor();
				}
			}
			img.updatePixels();
			return img;
		}

	private PImage resizenearesNeigbor(PImage img, int scale){

		int x = img.width*scale;
		int y = img.height*scale;
		nearestNeighbor.set("newsize", (float)x, (float)y);
		nearestNeighbor.set("oldsize", (float)img.width, (float)img.height);
		var pg = createGraphics(x, y, P2D);
		pg.beginDraw();
		pg.shader(nearestNeighbor);
		nearestNeighbor.set("tex", img);
		pg.rect(0, 0, x, y);
		pg.endDraw();
		resetShader();
		return pg.get();
	}
}
