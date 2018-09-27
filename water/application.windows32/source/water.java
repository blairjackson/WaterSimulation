import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class water extends PApplet {


int scl = 15;
int rows, cols;
int w = 1800;
int h = 1600;

float flying = 0;

float[][] terrain;

public void setup(){
  
  cols = w / scl;
  rows = h / scl;
  
  terrain = new float[cols][rows];
}

public void draw(){
  flying -= 0.007f;
  
  float yoff = flying;
  for(int y = 0; y < rows; ++y){
    float xoff = 0;
   for(int x = 0; x < cols; ++x){
      terrain[x][y]= map(noise(xoff, yoff), 0, 1, -20, 20);
      xoff += 0.1f;
   }
   yoff += 0.1f;
  }
  
  background(43, 0, 109);
  stroke(255);
  fill(90, 201, 252);
  translate(width/2, height/2);
  rotateX(PI/2.2f);
  translate(-w/2, -h/2);
  
  for(int y = 0; y < rows - 1; ++y){
    beginShape(TRIANGLE_STRIP);
   for(int x = 0; x < cols; ++x){
     vertex(x*scl, y*scl, terrain[x][y]);
     vertex(x*scl, (y+1)*scl, terrain[x][y + 1]);
   }
   endShape();
  }
}
  public void settings() {  size(800, 800, P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "water" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
