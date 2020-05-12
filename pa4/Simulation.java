//Josue Uriarte Reyes
//joauriar@ucsc.edu
//CS12M pa4
//03/07/19
//-----------------------------------------------------------------------------
// Simulation.java
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.*;
import java.text.*;
import java.util.Scanner;

public class Simulation{

//-----------------------------------------------------------------------------
//
// The following function may be of use in assembling the initial backup and/or
// storage queues.  You may use it as is, alter it as you see fit, or delete it
// altogether.
//
//-----------------------------------------------------------------------------

   public static Job getJob(Scanner in) {
      String[] s = in.nextLine().split(" ");
      int a = Integer.parseInt(s[0]);
      int d = Integer.parseInt(s[1]);
      return new Job(a, d);
   }

//-----------------------------------------------------------------------------
//
// The following stub for function main contains a possible algorithm for this
// project.  Follow it if you like.  Note that there are no instructions below
// which mention writing to either of the output files.  You must intersperse
// those commands as necessary.
//
//-----------------------------------------------------------------------------

   public static void main(String[] args) throws IOException{

//    1.  check command line arguments
		if(args.length < 1){
			System.out.println("Usage: Simulation <input file>");
			System.exit(1);
		}
//
//    2.  open files for reading and writing
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter report = new PrintWriter(new FileWriter(args[0]+".rpt"));
		PrintWriter trace = new PrintWriter(new FileWriter(args[0]+".trc"));
//
//    3.  read in m jobs from input file
		int jobs = Integer.parseInt(in.nextLine().trim());

		Job[] jobArray = new Job[jobs];

		int i=0;
		while(i < jobs){
			jobArray[i] = getJob(in);
			i++;
		}

		//Report File Outputs
		report.println("Report file: "+args[0]+".rpt");
		report.println(jobs + " Jobs:");
		i=0;
		while(i < jobs){
			report.print(jobArray[i].toString()+" ");
			i++;
		}
		report.println("\n\n***********************************************************");

		//Trace File Outputs
		trace.println("Trace file: "+args[0]+".trc");
		trace.println(jobs + " Jobs:");
		i=0;
		while(i < jobs){
			trace.print(jobArray[i].toString()+" ");
			i++;
		}
		trace.println("\n");

//
//    4.  run simulation with n processors for n=1 to n=m-1  {
		for(int n = 1; n <= jobs-1;n++){
//
//    5.      declare and initialize an array of n processor Queues and any 
//            necessary storage Queues
			Queue[] processArray = new Queue[n];
			Queue storageQueue = new Queue();
			int time = 0;
			boolean didSomething = false;
			int totalWait = 0;
			int maxWait = 0;
			double averageWait = 0;
			int minLength = 0;
			int index = 0;

			if(n == 1){
			trace.println("*****************************\n"+
						n + " processor:\n" + 
						"*****************************");
			}
			else{
			trace.println("*****************************\n"+
					n + " processors:\n" + 
					"*****************************");
			}

			i=0;
			while(i < jobs){
				storageQueue.enqueue(jobArray[i]);
				i++;
			}



			//Print statement at time 0
			trace.println("time="+time);
			trace.println("0: "+ storageQueue);
			for(i = 0; i < processArray.length; i++){
				processArray[i] = new Queue();
				trace.println(i+1 +":");
			}
			trace.println("");


			
			time = ((Job)storageQueue.peek()).getArrival();
			processArray[0].enqueue(storageQueue.peek());
			storageQueue.dequeue();

//    6.    while unprocessed jobs remain  {
			while(storageQueue.length() != jobs || ((Job)storageQueue.peek()).getFinish() == Job.UNDEF){

				didSomething = false;

				//Check if job needs to go back
				for(i = 0; i < processArray.length; i++){

					if(!(processArray[i].isEmpty())){

						if(((Job)processArray[i].peek()).getFinish() == time){
							storageQueue.enqueue(processArray[i].peek());
							processArray[i].dequeue();

							//From processor to storage
							didSomething = true;
						}

					}
				}
				
				//Check if there is an incoming job
				while(!(storageQueue.isEmpty())){

					minLength = processArray[0].length();
					index = 0;

					if(((Job)storageQueue.peek()).getArrival() == time){

						for(i = 0; i < processArray.length; i++){
							
							if (processArray[i].length() < minLength) {

								minLength = processArray[i].length();
								index = i;

							}

						}

						//Going from storage to processor
						processArray[index].enqueue(storageQueue.peek());
						storageQueue.dequeue();
						didSomething = true;	
						
					}

					else{
						break;
					}
				}

				//Check if a job can be finished
				for(i = 0; i < processArray.length; i++){
					if(!(processArray[i].isEmpty())){
						if(((Job)processArray[i].peek()).getFinish() == Job.UNDEF){

							((Job)processArray[i].peek()).computeFinishTime(time);

							//Getting total wait, maxWait, and averageWait
							totalWait += ((Job)processArray[i].peek()).getWaitTime();
							if(maxWait < ((Job)processArray[i].peek()).getWaitTime()){
								maxWait = 
((Job)processArray[i].peek()).getWaitTime();
							}
							averageWait = (double)totalWait/(double)jobs;

							//Finished Time
							didSomething = true;
						}
					}
				}

				//Check if you have to print
				if(didSomething == true){
					trace.println("time="+time);
					trace.println("0: "+ storageQueue);
					for(int k = 0; k < processArray.length; k++){
						trace.println( (k+1) +": "+processArray[k].toString());
					}
					trace.println("");
				}


				time++;
			}

//    11.     compute the total wait, maximum wait, and average wait for 
//            all Jobs, then reset finish times
			DecimalFormat df = new DecimalFormat("0.00"); 

			if(n == 1){
				report.print(n + " processor: totalWait="+totalWait+", maxWait="+maxWait);
				report.println(", averageWait=" + df.format(averageWait));
			}
			else{
				report.print(n + " processors: totalWait="+totalWait+", maxWait="+maxWait);
				report.println(", averageWait=" + df.format(averageWait));
			}

			i=0;
			storageQueue.dequeueAll();
			while(i < jobs){
				jobArray[i].resetFinishTime();
				storageQueue.enqueue(jobArray[i]);
				i++;
			}

		}

//    13. close input and output files
		in.close();
		report.close();
		trace.close();

   }
}

