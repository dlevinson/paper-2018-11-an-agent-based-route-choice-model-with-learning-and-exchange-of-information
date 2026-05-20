import java.io.*;
import java.text.*;

public class DirectedGraph {
	
	public float link_info[][];//brackets:1-link index; 2-attribute index 
	///link attributes
	//0-link ID (from 1)
	//1-Origin node ID
	//2-Destination node ID
	//3-link type (functional class)
	//4-length
	//5-free flow speed
	//6-number of lanes
	//7-capacity
	//8-traffic flow (AM peak hour)veh/hour?
	//9-BPR travel time
	//10-toll rate;
	
	public int edges, vertices;
	final static int link_attributes = 11;
	
	public DirectedGraph(String linkinfofile) throws IOException{
		
		ReadANumber read = new ReadANumber();
		FileInputStream fin = null;
		
		////read link information
		try {
			fin = new FileInputStream (linkinfofile);
		} catch(FileNotFoundException e) {
			System.out.println("From DirectedGraph class: Exception Occured!!!!");
			return;
		}
		
		edges = read.readint(fin);
		vertices=read.readint(fin);
		link_info = new float[edges][link_attributes];
		for (int i=0; i<edges;i++){
			for (int j=0;j<link_attributes;j++){
				link_info[i][j]=0;
			}
		}
		for (int i=0; i<edges;i++){
			for (int j=0;j<link_attributes;j++){
				link_info[i][j]=read.readfloat(fin);				
			}
			if(read.end == -1) break;
		}
		//System.out.println("End of dg constructor");
	}
	
	//Read integer or read float number
	class ReadANumber{
		
		public int end;
		
		ReadANumber() {
			end = 0;
		}
		
		int readint(InputStream f)
			throws IOException
		 {
			String msg = "";
			int i;
			do {
				i=f.read();
				//if(i != -1 && i != 13  && i!=32 && i!=9 )
				if(i>47 && i<58 || i==(int)'.'|| i==(int)'-')
				//////  32 ---- space, 13 ----- new Line
				msg += (char)i;
				
				//System.out.print("\tmsg="+msg);
			} while(i>47 && i<58 ||i==(int)'.'|| i==(int)'-');
			
			end = i;
		
			try {
				if(msg != null)  {
					//i = Integer.parseInt(msg);
					//return( i );
					if(msg.charAt( 0)=='-')return(-1*Integer.parseInt(msg.substring( 1)));
					else return(Integer.parseInt(msg) );

				}
				else
					return ( 0 );
			}	catch(NumberFormatException e) {
				System.out.println("NumberFormatException integer.");
				return ( 0 );
			}
		}
		
		
		
		float readfloat(InputStream f) 
			throws IOException
		{
			String msg = "";
			int i;
			
			do {
				
				i = f.read();
				//System.out.print("\ti="+i);
				//if(i != -1 && i != 13 && i != 32 && i!=9 && i!=10)
				if(i>47 && i<58 || i==(int)'.' ||i=='-')
				msg += (char)i;
			} while(i>47 && i<58 || i==(int)'.'||i=='-');
			
			end = i;
		
			try {
				if(msg.charAt( 0) != 0)  {
					if(msg.charAt( 0)=='-')return(-1*Float.valueOf(msg.substring( 1)).floatValue());
					else return( Float.valueOf(msg).floatValue() );
				}	
				else
					return ( 0 );
			}	catch(NumberFormatException e) {
				System.out.println("NumberFormatException float");
				return (0);
			}
		}
		
	}
//	///////////////////////    End of ReadANumber class
}
