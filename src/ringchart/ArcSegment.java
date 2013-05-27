package ringchart;

import processing.core.*;


public class ArcSegment{
  float centerx, centery, radius, arcwidth, arcstart, extent, left, top;
  PApplet parr;

  ArcSegment(float centerx, float centery, float radius, float arcwidth, float arcstart, float extent, PApplet p) {
	parr = p;
    this.centerx = centerx;
    this.centery = centery;
    this.radius = radius;
    this.arcwidth = arcwidth;
    this.arcstart = arcstart;
    this.extent = extent;
    this.left = centerx - radius;
    this.top = centery - radius;
    //println(radius+":"+arcwidth+":"+arcstart+":"+extent);
  }
  
  //source: http://www.shiffman.net/2011/02/03/rotate-a-vector-processing-js/
  void rotate2D(PVector v, float theta) {
    float xTemp = v.x;
    v.x = v.x*parr.cos(theta) - v.y*parr.sin(theta);
    v.y = xTemp*parr.sin(theta) + v.y*parr.cos(theta);
  }
  
  boolean mouseOver() {
    PVector v = new PVector(parr.mouseX-centerx,parr.mouseY-centery);
    PVector v0 = new PVector(radius/2,0);
    float mouseAngle = parr.degrees(PVector.angleBetween(v, v0));
    float mouseLength = v.mag();
    if (parr.mouseY < centery) mouseAngle = 360-mouseAngle;
    return (mouseLength > radius-arcwidth && mouseLength < radius && mouseAngle > arcstart && mouseAngle < arcstart+extent);
  }

  public void draw() {
    //outer arc
    //parr.noStroke();
	//parr.strokeWeight(3);
	//parr.strokeCap(parr.ROUND);
	//parr.stroke(0);
    parr.arc(left, top, 2 * radius, 2 * radius, parr.radians(arcstart), parr.radians(arcstart+extent));

    //inner arc
    //parr.fill(255);
    //ellipse(left + arcwidth, top + arcwidth, 2 * radius - 2 * arcwidth, 2 * radius - 2 * arcwidth);
    //parr.noFill();
    parr.arc(left + arcwidth, top + arcwidth, 2 * radius - 2 * arcwidth, 2 * radius - 2 * arcwidth, parr.radians(arcstart), parr.radians(arcstart+extent));
    
    //lines to connect arcs
    parr.noStroke();
    PVector v = new PVector(radius/2,0);
    rotate2D(v,parr.radians(arcstart));
    parr.line(centerx+v.x,centery+v.y,centerx+v.x*2,centery+v.y*2);
    rotate2D(v,parr.radians(extent));
    parr.line(centerx+v.x,centery+v.y,centerx+v.x*2,centery+v.y*2);
  }
}
