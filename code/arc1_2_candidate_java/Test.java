/*
 * Agent-based Route Choice model1.0
 * Sep, 2006
 * Shanjiang Zhu
 */
import java.io.*;
import java.text.*;
public class Test {
	final static int max_year = 2;
	final static double max_oderror = 30;
	final static double large = 999;
		
	Test() throws IOException{
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		int strategy =2;
		//=1 if test ARC alone;
		//=2 if test NODE & ARC;
		
		String linkinfofile;
		String odinfofile;
//		linkinfofile = "V7Net400.txt";
//		odinfofile = "V7NetOD400.txt";
		linkinfofile = "SiouxFallsNet.txt";
		odinfofile = "SiouxFallsOD.txt";
		DirectedGraph dg;
		Evolve evolve;
		dg = new DirectedGraph(linkinfofile);
		evolve = new Evolve(dg,odinfofile);
		if (strategy == 1){
			evolve.initialization();
			evolve.iteration();
		}else if(strategy ==2){
			evolve.odInitialization();
			evolve.odEstimator();
			evolve.resetInfo();
			double error;
			error = large;
			int year = 1;
			while ((year<max_year) && (error>max_oderror)){    //???Why max_year???
				//System.out.println("************Year"+year+"*********");
				evolve.initialization();
				evolve.iteration();
				evolve.updateDestination();
				error = evolve.odError();
				evolve.resetInfo();
				//System.out.println("End of Year"+year+"\tError:"+error);
				year++;
			}
		}
		//End of iteration, output result;
		String outfilename;
		outfilename = "Out.txt";
		evolve.outputfile(outfilename,dg);
		System.out.println("************END*********");
	}

}