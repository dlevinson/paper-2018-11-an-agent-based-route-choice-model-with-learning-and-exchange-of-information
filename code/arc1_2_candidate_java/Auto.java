import java.io.*;
import java.awt.*;
import java.applet.*;
import java.net.*;   
import java.util.*;
import java.lang.*;

public class Auto {
	public int id;
	public int origin;
	public int destination;
	public int status;
	public int currentNode;
	public int previousNode;
	public int currentposition; // the index that indicate position on the route;
	public double vot;			//value of time, unit in $/min;
	public double travelbudge;	//
	
	final static int path_info_attributes = 2;
	final static int route_info_attributes = 3;
	final static int large = 50; //the largest number of nodes on each path;
	final static int nullindicator = -9;
	final static int infinite = 999;
	
	public int path[];
	public double path_info[];
	//0: nodes on the route;
	//1: time cost;
	public int route[];
	public double route_info[];
	//0: nodes on the route;
	//1: time cost;
	//2: total money cost;
	
	public Auto(int id, int origin, int destination, double vot) {
		this.id = id;
		this.origin = origin;
		this.destination = destination;
		this.status = 0;
		this.currentNode = origin;
		this.previousNode = this.currentNode;
		this.currentposition = 0;
		this.path = new int[large];
		this.path_info = new double[path_info_attributes];
		this.route = new int[large];
		this.route_info = new double[route_info_attributes];
		this.vot = vot;
		this.travelbudge = 0;
		
		path[0] = origin;
		for (int i=1;i<large;i++){
			path[i] = nullindicator;
		}
		path_info[0] = 1;
		path_info[1] = infinite;
		
		for (int i=0;i<large;i++){
			route[i] = large;
		}
		route_info[0] = nullindicator;
		route_info[1] = infinite;
		route_info[2] = infinite;
	}
	
	public boolean addNode(int nextNode, double linkcost){
		boolean existCircle=false;
		if (nextNode == this.destination){
			this.previousNode = this.currentNode;
			this.currentNode = nextNode;
			this.status =1;
			this.path_info[0] = this.path_info[0]+1;
			int pathindex;
			pathindex = (int)this.path_info[0]-1;
			this.path[pathindex] = nextNode;
			this.path_info[1] = this.path_info[1]+linkcost;
		}else{
			int breakindex=0;
			int index = (int)this.path_info[0]-1;
			while (index>=0 && existCircle==false){
				if (this.path[index] == nextNode){
					existCircle = true;
					breakindex = index;
				}else{
					index--;
				}
			}
			if (existCircle == false){
				this.previousNode = this.currentNode;
				this.currentNode = nextNode;
				this.path_info[0] = this.path_info[0]+1;
				int pathindex;
				pathindex = (int)this.path_info[0]-1;
				this.path[pathindex] = nextNode;
				this.path_info[1] = this.path_info[1]+linkcost;
			}else{
				this.previousNode = this.currentNode;
				this.currentNode = nextNode;	//which is the same as path[breakindex];
				int pathindex;
				pathindex = (int)this.path_info[0]-1;
				while(pathindex>breakindex){
					this.path[pathindex]=nullindicator;
					this.path_info[0] -= 1;
					pathindex--;
				}		
			}
		}
		return(existCircle);
	}
	
	public void pathIncrement(int nextNodeId, double linkcost){
		this.path_info[0] = this.path_info[0]+1;
		int pathindex;
		pathindex = (int)this.path_info[0]-1;
		this.path[pathindex] = nextNodeId;	//Add the next node to node chain of path;
		this.path_info[1] = this.path_info[1]+linkcost;	//Add the length cost to total pathcost;
	}
	
	public void copypathToroute(){
		//Copy current path to route choice;
		for (int i=0;i<large;i++){
			this.route[i]=this.path[i];
		}
		for (int i=0;i<path_info_attributes;i++){
			this.route_info[i] = this.path_info[i];
		}
	}
	
	public void reset(){
		//Used in case erase both path and route infor;
		//travelbudge, vot, origin, destination, id remain unchanged;
		this.status = 0;
		this.currentNode =this.origin;
		this.previousNode = this.origin;
		this.currentposition = 0;
		this.path = new int[large];
		this.path_info = new double[path_info_attributes];
		this.route = new int[large];
		this.route_info = new double[route_info_attributes];
		path[0] = origin;
		for (int i=1;i<large;i++){
			path[i] = nullindicator;
		}
		path_info[0] = 1;
		path_info[1] = infinite;
		
		for (int i=0;i<large;i++){
			route[i] = large;
		}
		route_info[0] = nullindicator;
		route_info[1] = infinite;
		route_info[2] = infinite;
	}
	
}
