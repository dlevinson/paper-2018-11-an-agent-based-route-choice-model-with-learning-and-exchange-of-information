import java.io.*;
import java.awt.*;
import java.applet.*;
import java.net.*;   
import java.util.*;
import java.lang.*;

public class Node {
	public int id;
	
	final static int option_number = 10;	//number of shortest path stored at nodes;
	final static int path_info_attributes = 2;
	final static int large = 50; //the largest number of nodes on each path;
	final static int nullindicator = -9;
	final static int infinite = 999;
	
	public int path[][][];	//Record the shortest path from ith node to current node;
	//first bracket: from node index: nodeid-1;
	//second bracket: nth shortest path; start from 3 options;
	//third bracket: store each nodeid on the path;
	public double path_info[][][];
	//first bracket: from node index: nodeid-1;
	//second bracket: nth shortest path; start from 3 options;
	//thir bracket:
	//0: number of nodes on path including origin and destination;
	//1: total cost;
	
	
	public Node(int id, int numNodes){
		this.id = id;
		this.path = new int[numNodes][option_number][large];
		this.path_info = new double[numNodes][option_number][path_info_attributes];
		
		for (int i=0;i<numNodes;i++){
			for (int j=0;j<option_number;j++){
				path[i][j][0] = id;
				for (int k=1;k<large;k++){
					path[i][j][k] = nullindicator;
				}
				path_info[i][j][0] = 1;
				path_info[i][j][1] = infinite;
			}
		}
	}
}
