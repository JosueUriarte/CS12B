class Queens{

	public static void main(String[] args){

		if(args.length < 1){
			System.out.println("Usage: Queens [-v] number");
			System.out.println("Option: -v verbose output, print all solutions");
			System.exit(1);
		}

		try{
			if(args.length == 1){
				int size = Integer.parseInt(args[0]);
				int [][] board = new int[size+1][size+1];
				System.out.println(size+"-Queens has " + findSolutions(board, 1, "") + " solutions");
			}

			if(args.length == 2){
				int size = Integer.parseInt(args[1]);
				String mode = args[0];

				if(!(mode.equals("-v"))){
					System.out.println("Usage: Queens [-v] number");
					System.out.println("Option: -v verbose output, print all solutions");
					System.exit(1);
				}

				int [][] board = new int[size+1][size+1];
				System.out.println(size+"-Queens has " + findSolutions(board, 1, mode) + " solutions");
			}
		}
		
		catch(Exception e){
			System.out.println("Usage: Queens [-v] number");
			System.out.println("Option: -v verbose output, print all solutions");
			System.exit(1);
		}
	}


	static int findSolutions(int[][] B, int i, String mode){
		//if i > n (a queen placed on row n hence solution)
			//return 1
		int total = 0;

		if(i > B.length-1){
			//System.out.println("aha!");
			if(mode.equals("-v")){
				printBoard(B);
			}
			B[0][0] += 1;
			return 1;
		}	

		else{

			for(int n = 1; n < B.length; n++){

				//System.out.println(i + " " + n);
				if(B[i][n] == 0){
					//System.out.println(i + " " + n + " is zero " + B[i][n]);
					placeQueen(B, i, n);
					findSolutions(B, i+1, mode);
					removeQueen(B, i, n);
					

				}
			}
		}
		return B[0][0];
	}

	static void printBoard(int[][] B){

		System.out.print("(");
		for(int k = 1; k < B.length; k++){

			if(k == B.length-1){
				System.out.print(B[k][0]);
			}
			else{
				System.out.print(B[k][0] + ", ");
			}
		}
		System.out.print(")");

		System.out.println();
	}

	static void placeQueen(int[][] B, int i, int j){
		B[i][j] += 1;
		B[i][0] = j;

		//Down and Across
		for(int k = 1; k < B.length; k++){

			if(i+k < B.length){
				B[i+k][j] -= 1;
			}

			if((j+k) < B.length && (i+k) < B.length){
				B[i+k][j+k] -= 1;
			}

			if((j-k) > 0 && (i+k) < B.length){
				//System.out.println((i+k) + " " + (j-k));
				B[i+k][j-k] -= 1;
			}
		}
	}

	static void removeQueen(int[][] B, int i, int j){
		B[i][j] = 0;
		B[i][0] = 0;

		//Down and Across
		for(int k = 1; k < B.length; k++){

			if(i+k < B.length){
				B[i+k][j] += 1;
			}

			if((j+k) < B.length && (i+k) < B.length){
				B[i+k][j+k] += 1;
			}

			if((j-k) > 0 && (i+k) < B.length){
				//System.out.println((i+k) + " " + (j-k));
				B[i+k][j-k] += 1;
			}
		}
	}
}

