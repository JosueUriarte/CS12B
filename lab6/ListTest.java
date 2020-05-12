//Josue Uriarte Reyes
//joauriar@ucsc.edu
//CS12M lab6
//03/04/19
public class ListTest{

	public static void main(String[] args){
		List<String> A = new List<String>();
		List<Integer> B = new List<Integer>();
		List<String> C = new List<String>();
    	List<List<String>> D = new List<List<String>>();

		A.add(1, "Hey!");
		A.add(2, "NO!");
		System.out.println("A: "+A);
		System.out.println("A.size() is "+A.size());

		B.add(1, 420);
		B.add(2, 711);
		System.out.println("B: "+B);
		System.out.println("B.size() is "+B.size());

		C.add(1, "WHOA");
		C.add(2, "yeah");
		System.out.println("C: "+C);
		System.out.println("C.size() is "+C.size());

		D.add(1, A);
		D.add(2, C);
		System.out.println("D: "+D);
		System.out.println("D.size() is "+D.size());

		System.out.println();
		try{
         System.out.println(A.get(200));
      }catch(ListIndexOutOfBoundsException e){
         System.out.println("Caught Exception: ");
         System.out.println(e);
         System.out.println("Continuing without interruption");
      }
    	System.out.println();

    	B.remove(1);
    	A.remove(2);
    	D.remove(1);
    	System.out.println("B.get(1) is "+B.get(1));
    	System.out.println("A.get(1) is "+A.get(1));
    	System.out.println("D.get(1) is "+D.get(1));




	}




}
