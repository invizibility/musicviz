
package ringchart;

import processing.core.*;
import megamu.shapetween.*;
import java.util.*;


public class Ring
{
	
	PApplet parr;
	ArrayList<String> Labels;
	ArrayList<Float> Values;
	ArrayList<ArrayList<Float>> Colors;
	float x, y, radius, ringwidth, ringstart, ringend;
	ArcSegment[] segments;
	float increm = 0;
	boolean froze = false;
	boolean switchdir = false; 
	int checker;
// Tween heat01 = new Tween();
//	Tween heat02 = new Tween(parr, (float)2.2);
//	Tween heat10 = new Tween(parr, (float)2.2);
//	Tween heat12 = new Tween(parr, (float)2.2);
//	Tween heat21 = new Tween(parr, (float)2.2);
//	Tween heat20 = new Tween(parr, (float)2.2);
	

	
	
	Ring(PApplet p) 
	{
		parr = p;
	    Labels = new ArrayList<String>();
	    Values = new ArrayList<Float>();
	    //Colors = new ArrayList<Float>();
	    Colors = new ArrayList<ArrayList<Float>>();
	    Colors.add(new ArrayList<Float>());
	    Colors.add(new ArrayList<Float>());
	    Colors.add(new ArrayList<Float>());
	    this.ringstart = 0;
	    this.ringend = 360;
	    this.segments = null;
	    
	}
	
	void setStart(float ringstart) 
	{
	    this.ringstart = ringstart;
	}
	
	void setEnd(float ringend) 
	{
	    this.ringend = ringend;
	}
	
	int count() 
	{
	    return Values.size();
	}
	
	String getLabel(int index) 
	{
	    return Labels.get(index);
	}
	  
	
	  /*
	  color getColor(int index) {
	    return Colors.get(index);
	  }
	  */
	
	  
	void setCenter(float x, float y) 
	{
	    this.x = x; 
	    this.y = y;
	}
	  
	void setswitch()
	{
	    this.switchdir = true;
	}
	  
	
	void setRadius(float radius) 
	{
	    this.radius = radius;
	}
	
	void setRingWidth(float ringwidth) 
	{
	    this.ringwidth = ringwidth;
	}
	
	
	//col1 = creation time
	//col2 = access time
	//col3 = modification time
	void addItemTest(String label, float volume, float col1, float col2, float col3)
	{
	    Labels.add(label);
	    Values.add(volume);
	    Colors.get(0).add(parr.map(col1,(float)1150000000,(float)1400000000,0,255));
	    Colors.get(1).add(parr.map(col2,(float)1150000000,(float)1400000000,0,255));
	    Colors.get(2).add(parr.map(col3,(float)1150000000,(float)1400000000,0,255));
	}
	void addItem(String label, float val)
	{
		Labels.add(label);
		Values.add(val);
		Colors.get(0).add(parr.random(0,255));
		Colors.get(1).add(parr.random(0,255));
		Colors.get(2).add(parr.random(0,255));
	}
	
	ArcSegment getSegment(int index) 
	{
	    return segments[index];
	}
	
	void createSegments() 
	{
	    segments = new ArcSegment[Values.size()];
	    float sum = 0;
	    float span = this.ringend - this.ringstart;
	    for (int i=0; i<Values.size(); i++) 
	    {
	    	sum += Values.get(i);
	    }
	    //pushMatrix();
	    
	    float strt = (switchdir)?this.ringstart-increm:this.ringstart+increm;
	    for (int i=0; i<Values.size(); i++) 
	    {
		    float extent = (Values.get(i) / sum) *span;
		    ArcSegment segment = new ArcSegment(this.x, this.y, this.radius, this.ringwidth, strt, extent, parr);
		    this.segments[i] = segment;
		    strt += extent;
	    }
	    if(!froze)
	    {
	    	increm=0;
	      
	    }
	    if(increm==360)
	        increm=0;
	    
	    
	    //popMatrix();
	}
	  
	boolean myMouse()
	{
		for(int i=0; i<segments.length; i++)
	    {
	    	if (segments[i].mouseOver())
	    		return true;
	    }
	    
	    return false;
	}
	
	public void draw(int curmap, int premap, int alpha, Tween ani) 
	{
	/*
	    if(froze){
	      if(!myMouse())
	        froze = false;
	    }
	*/    
	    checker = 0;
	    for (int i=0; i<segments.length; i++) {
	    	//use colors array to store age heatmaps
	    	//use filesize array to store filesizes (tween to whiteout scale, or tween back to FS colorscale from NF whiteout)
	    	//use numfiles array to store numfiles (tween to whiteout scale, or tween back to NF colorscale from FS whiteout)
	    	
	      if (segments[i].mouseOver()) {
	        //parr.noStroke();
	    	parr.fill(0);
	        froze = true;
	        //text(Labels.get(i),20,20);
	        checker+=1;
	        //System.out.println("hit"+parr.millis());
	      }
	      else if(curmap==0){
	    	//parr.noStroke();
	    	//parr.stroke(255);
	    	if(ani.isTweening()) {
	    		if(premap==1) {
	    			int precol = parr.color(255,Colors.get(1).get(i),0);
	    			int curcol = parr.color(Colors.get(0).get(i),255,0);
	    			int interpo = parr.lerpColor(precol,curcol, ani.time());
	    			parr.println(ani.time());
	    			
	    			parr.fill(interpo);
	    			ani.tick();
	    			
	    		}
	    		
	    		else if(premap==2) {
	    			int precol = parr.color(0,Colors.get(2).get(i),255);
	    			int curcol = parr.color(Colors.get(0).get(i),255,0);
	    			int interpo = parr.lerpColor(precol, curcol, ani.time());
	    			parr.println(ani.time());
	    			parr.fill(interpo);
	    			ani.tick();
	    		}
	    	}
	    	else {
	    		
	    		parr.fill(Colors.get(0).get(i),255,0);
	    	}
//	    	else if(premap==1) {
//	    		
//	    		//parr.fill();
//	    		
//	    	}
//	    	else if(premap==2) {
//	    		
//	    	}
	        
	        
	      }
	      else if(curmap==1){
	    	parr.fill(240,Colors.get(1).get(i),0); 
	      }
	      else if(curmap==2){
	    	parr.fill(0,Colors.get(2).get(i),240); 
	      }	      
	      segments[i].draw();
	    }
	    if(checker==0)
	      froze=false;
	    
	    //text("dgfdfgd",30,30);
	  }
}
