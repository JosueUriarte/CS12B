import java.io.*;
import java.util.Scanner;

class Search{

	public static void main(String[] args){

    	// check number of command line arguments
    	if(args.length < 2){
        	System.err.println("Usage: Search file target1 [target2 ..]");
        	System.exit(1);
      	}
      
     	try{
	 	// count the number of lines in file
	  		Scanner in = new Scanner(new File(args[0]));
	    	in.useDelimiter("\\Z"); // matches the end of file character
	    	String s = in.next();   // read in whole file as a single String
	    	in.close();

	    	String[] words = s.split("\n");  // split s into individual lines
	    	int[] lineNum = new int[words.length];

	    	for(int i = 0; i < words.length; i++ ){
      			lineNum[i] = i+1;
      		}

     		mergeSort(words, lineNum, 0, words.length-1);

    		for(int i = 1; i < args.length;i++){

    			int binaryNum = binarySearch(words, 0, words.length-1, args[i]);

    			//System.out.println("on word: " + args[i]);
    			//System.out.println("binary: " + binaryNum);

    			if(binaryNum < 0){
      				System.out.println(args[i] + " not found");
      			}

      			if(binaryNum >= 0){
      				System.out.println(args[i] + " found on line " + lineNum[binaryNum]);
      			}
	    	}
	    }

	  	catch(FileNotFoundException e){
	  		System.out.println(args[0] + " (No such file or directory)");
	  		System.err.println("Usage: Search file target1 [target2 ..]");
	  		System.exit(1);
	    }
	
	}

	static int binarySearch(String[] A, int p, int r, String t){
		int q;

    	if(p > r) {
        	return -1;
    	}

    	else{
        	q = (p+r)/2;
        	if(t.equals(A[q])){
        		//System.out.println(t.compareTo(A[q]));
           		return q;
        	}

        	else if((t.compareTo(A[q])) < 0){
        		//System.out.println(t.compareTo(A[q]));
            	return binarySearch(A, p, q-1, t);
        	}

        	else{ // t > A[q]
        		//System.out.println(t.compareTo(A[q]));
            	return binarySearch(A, q+1, r, t);
        	}

    	}
	}


	static void mergeSort(String[] word, int[] A, int p, int r){
		int q;
    	if(p < r) {
        	q = (p+r)/2;
        	// System.out.println(p+" "+q+" "+r);
        	mergeSort(word, A, p, q);
        	mergeSort(word, A, q+1, r);
        	merge(word, A, p, q, r);
    	}


	}

	static void merge(String[] word, int[] A, int p, int q, int r){
		int n1 = q-p+1;
      	int n2 = r-q;
      	String[] L = new String[n1];
      	String[] R = new String[n2];

      	int[] l2 = new int[n1];
      	int[] r2 = new int[n2];


      	int i, j, k;

      	for(i=0; i<n1; i++){
         	L[i] = word[p+i];
         	l2[i] = A[p+i];
      	}
      	for(j=0; j<n2; j++){ 
         	R[j] = word[q+j+1];
         	r2[j] = A[q+j+1];
      	}

      	i = 0; j = 0;
      	for(k = p; k <= r; k++){
         	if(i < n1 && j < n2){
            	if( L[i].compareTo(R[j]) < 0 ){
               		word[k] = L[i];
               		A[k] = l2[i];
               		i++;
            	}
            	else{
               		word[k] = R[j];
               		A[k] = r2[j];
               		j++;
            	}
         	}
			else if( i < n1 ){
            	word[k] = L[i];
            	A[k] = l2[i];
            	i++;
         	}
         	else{ // j < n2
            	word[k] = R[j];
            	A[k] = r2[j];
            	j++;
         	}
      	}
	}

}
