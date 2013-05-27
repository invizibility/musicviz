package ringchart;

import java.io.*;
import processing.core.PApplet;
import megamu.shapetween.*;
import java.util.*;

public class RingChart extends PApplet {
	
	Ringzz chart = new Ringzz(this);
	

	public void setup() {
		  size(1300,750,OPENGL);
		  
		  //smooth();
		  ellipseMode(CORNER);

		  ArrayList<Ring> rings = new ArrayList<Ring>();
		  
		  ArrayList<Ring> testringz = new ArrayList<Ring>();
		  try {
			  
			  BufferedReader myread = createReader("folderdata_utf8.txt");
			  //String line = myread.readLine();
			  //System.out.println(line);
			  try {
				Ring tempring = null;
				String topstring = null;
				PrintStream out = new PrintStream(System.out, true, "UTF-8");
				char first;
				//boolean resetswitch = false; //on the first level, we want to read from the beginning o
				while(true) {
					//if(resetswitch)
						//myread.reset();
					String line = myread.readLine();
					if(line==null) // end of file
						break;
					topstring = new String(line.getBytes("UTF-8"), "UTF-8");
					//out.println(new String(topstring.getBytes("UTF-8")));
					first = topstring.charAt(0);
					//scan file for level indicators
					if(first=='-') {
						/*
						if(tempring!=null) {
							testringz.add(tempring);
						}
						*/ 
						tempring = new Ring(this);
						String[] topparse = topstring.split(" ");
						int numfolds = Integer.parseInt(topparse[1]);
						for(int x=0;x<numfolds;x++) {
							String folderline = new String(myread.readLine().getBytes("UTF-8"),"UTF-8");
							String[] parseline = folderline.split(java.util.regex.Pattern.quote(" ||| "));
							tempring.addItemTest(parseline[0], Float.parseFloat(parseline[3]), Float.parseFloat(parseline[4]), Float.parseFloat(parseline[5]), Float.parseFloat(parseline[6]));
						}
						
						//add folders to level then kick back up to level scanning once level is complete
						//This way of doing it turned out to not work
						/*
						while(true) {
							String folderline = myread.readLine();
							
							if(folderline==null)
								break;
							char folderfirst = folderline.charAt(0);
							if(folderfirst=='H') {
								
								String[] parseline = folderline.split(java.util.regex.Pattern.quote(" ||| "));
								tempring.addItemTest(parseline[0], Float.parseFloat(parseline[3]), Float.parseFloat(parseline[4]), Float.parseFloat(parseline[5]), Float.parseFloat(parseline[6]));
							}
							else if(folderfirst=='-') {
								
								myread.mark(0);
								if(!resetswitch)
									resetswitch = true;
								break;
							}
							
						
							
							
							//System.out.println(parseline.length);
							
						}
						*/
						testringz.add(tempring);
						
					}
					
				}
			    
			   

				out.println("ARRAY SIZE: " + testringz.size());  
			  } catch (Exception e) {
				  e.printStackTrace();
				  
			  }
			  
			  
		  } catch (Exception e) {
			  e.printStackTrace();
			  
		  }
		 
		  
		  //String[] lines = loadStrings("folderdata.txt");
// test rings
		  /*
		  Ring ring = new Ring(this);
		  ring.setStart(0);
		  ring.setEnd(360);
		  ring.addItem("First Ring Item", 20);
		  ring.addItem("Second Ring Item", 11);
		  ring.addItem("Third Ring Item", 31);
		  ring.addItem("Fourth Ring Item", 12);
		  ring.addItem("Ring Item Five", 35);
		  ring.addItem("Ring Item Six", 28);
		  ring.addItem("Ring Item 7", 26);
		  rings.add(ring);

		  ring = new Ring(this);
		  ring.setStart(0);
		  ring.setEnd(360);
		  ring.addItem("Ring2: First Ring Item", 400);
		  ring.addItem("Ring2: Second Ring Item", 118);
		  ring.addItem("Ring2: Third Ring Item", 312);
		  for(int i=0;i<400;i++){
		    ring.addItem("dfgdfg", 15);
		  }
		  ring.addItem("Ring2: Fourth Ring Item", 125);
		  ring.addItem("Ring2: Ring Item Five", 350);
		  ring.addItem("Ring2: Ring Item Six", 285);
		  ring.addItem("Ring2: Ring Item 7", 265);
		  ring.setswitch();
		  rings.add(ring);

		 
		  ring = new Ring(this);
		  ring.setStart(0);
		  ring.setEnd(360);
		  ring.addItem("Ring2: First Ring Item", 400);
		  ring.addItem("Ring2: Second Ring Item", 118);
		  ring.addItem("Ring2: Third Ring Item", 312);
		  ring.addItem("Ring2: Fourth Ring Item", 125);
		  ring.addItem("Ring2: Ring Item Five", 350);
		  ring.addItem("Ring2: Ring Item Six", 285);
		  ring.addItem("Ring2: Ring Item 7", 265);
		  for(int i=0;i<200;i++){
		    ring.addItem("dfgdfg", 15);
		  }
		  rings.add(ring);
		  
		  ring = new Ring(this);
		  ring.setStart(0);
		  ring.setEnd(360);
		  ring.addItem("Ring2: First Ring Item", 400);
		  ring.addItem("Ring2: Second Ring Item", 118);
		  ring.addItem("Ring2: Third Ring Item", 312);
		  ring.addItem("Ring2: Fourth Ring Item", 125);
		  ring.addItem("Ring2: Ring Item Five", 350);
		  ring.addItem("Ring2: Ring Item Six", 285);
		  ring.addItem("Ring2: Ring Item 7", 265);
		  ring.setswitch();
		  rings.add(ring);
		  
		  ring = new Ring(this);
		  ring.setStart(0);
		  ring.setEnd(360);
		  ring.addItem("Ring2: First Ring Item", 400);
		  ring.addItem("Ring2: Second Ring Item", 118);
		  ring.addItem("Ring2: Third Ring Item", 312);
		  ring.addItem("Ring2: Fourth Ring Item", 125);
		  ring.addItem("Ring2: Ring Item Five", 350);
		  ring.addItem("Ring2: Ring Item Six", 285);
		  ring.addItem("Ring2: Ring Item 7", 265);
		  rings.add(ring);
		  
		  ring = new Ring(this);
		  ring.setStart(0);
		  ring.setEnd(360);
		  ring.addItem("Ring2: First Ring Item", 400);
		  ring.addItem("Ring2: Second Ring Item", 118);
		  ring.addItem("Ring2: Third Ring Item", 312);
		  ring.addItem("Ring2: Fourth Ring Item", 125);
		  ring.addItem("Ring2: Ring Item Five", 350);
		  ring.addItem("Ring2: Ring Item Six", 285);
		  ring.addItem("Ring2: Ring Item 7", 265);
		  ring.setswitch();
		  rings.add(ring);

		  ring = new Ring(this);
		  ring.setStart(0);
		  ring.setEnd(360);
		  ring.addItem("Ring2: First Ring Item", 400);
		  ring.addItem("Ring2: Second Ring Item", 118);
		  ring.addItem("Ring2: Third Ring Item", 312);
		  ring.addItem("Ring2: Fourth Ring Item", 125);
		  ring.addItem("Ring2: Ring Item Five", 350);
		  ring.addItem("Ring2: Ring Item Six", 285);
		  ring.addItem("Ring2: Ring Item 7", 265);
		  rings.add(ring);
		  */
		 chart.setRings(testringz);
	}

	public void draw() {
		background(255);
		chart.draw();
		
	}
}
