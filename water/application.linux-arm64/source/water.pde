
int scl = 15;
int rows, cols;
int w = 1800;
int h = 1600;

float flying = 0;

float[][] terrain;

void setup(){
  size(800, 800, P3D);
  cols = w / scl;
  rows = h / scl;
  
  terrain = new float[cols][rows];
}

void draw(){
  flying -= 0.007;
  
  float yoff = flying;
  for(int y = 0; y < rows; ++y){
    float xoff = 0;
   for(int x = 0; x < cols; ++x){
      terrain[x][y]= map(noise(xoff, yoff), 0, 1, -20, 20);
      xoff += 0.1;
   }
   yoff += 0.1;
  }
  
  background(43, 0, 109);
  stroke(255);
  fill(90, 201, 252);
  translate(width/2, height/2);
  rotateX(PI/2.2);
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
