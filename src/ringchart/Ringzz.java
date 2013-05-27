package ringchart;

import processing.core.*;
import megamu.shapetween.*;
import java.util.*;

public class Ringzz{
	PApplet parr;
	ArrayList<Ring> rings;
	int fillval = 0;
	int lastfill = 0;
	int transo = 0;
	int alpha = 0;
	Tween mapswitch = new Tween(parr, (float)1, Tween.SECONDS, Tween.ONCE);

  Ringzz(PApplet p) {
    rings = new ArrayList();
    parr = p;
  }

  void setRings(ArrayList<Ring> rings){
    this.rings = rings;
  }
  
  
  public void draw() {
    //draw from bigger to smaller rings
      if(parr.keyPressed) {
    	  mapswitch.start();
    	  if (parr.key == parr.CODED) {
    		  if(parr.keyCode == parr.RIGHT) {
    			  fillval = 0;
    			  if(lastfill!=fillval) {
    				  //mapswitch.start();
    				  transo = lastfill;
    				  
    				  lastfill = fillval;
    				  
    			  }
    		  }
    		  else if(parr.keyCode == parr.DOWN) {
    			  fillval = 1;
    			  if(lastfill!=fillval) {
    				  transo = lastfill;
    				  //mapswitch.start();
    				  lastfill = fillval;
    				  
    			  }    			  
    		  }
    		  else if(parr.keyCode == parr.LEFT) {
    			  fillval = 2;
    			  if(lastfill!=fillval) {
    				  transo = lastfill;
    				  //mapswitch.start();
    				  lastfill = fillval;
    				  
    			  }    			  
    		  }
    	  }
      }
      for (int r = rings.size()-1; r >= 0; r--) {
	      Ring ring = rings.get(r);
	      float rw = (parr.min(parr.width,parr.height) / (rings.size()))/2 - 20;
	      ring.setRingWidth(rw);
	      ring.setRadius(5*rw + rw*r);
	      ring.setCenter(parr.width/2, parr.height/2);
	      ring.createSegments();
	
	      ring.draw(fillval, transo, alpha, mapswitch);
	      /* 
	      for (int index = 0; index < ring.count(); index++) {
	        ring.draw();
	      }
	      */
    }
    parr.fill(255);
    Ring lowring = rings.get(3);
    parr.ellipseMode(parr.CENTER);
    parr.ellipse(parr.width/2,parr.height/2,lowring.radius,lowring.radius);
    parr.fill(0);
    String s1 = parr.mouseX + "," + parr.mouseY;
    parr.text(s1,parr.width/2,parr.height/2);
    parr.ellipseMode(parr.CORNER);  
  
  }
  

}
