//Josue Uriarte Reyes
//joauriar@ucsc.edu
//CS12M lab7
//03/11/19
public class Dictionary implements DictionaryInterface{


//Private Functions ----------------------------

	private class Node {
      String key;
      String value;
      Node left;
      Node right;

      Node(String key, String value){
         this.key = key;
         this.value = value;
         left = null;
         right = null;
      }
   	}

	private Node findKey(Node R, String k){
	   if(R==null || k.compareTo(R.key)==0) 
	      return R;
	   if( k.compareTo(R.key)<0 ) 
	      return findKey(R.left, k);
	   else // strcmp(k, R->key)>0
	      return findKey(R.right, k);
	}

	private Node findParent(Node N, Node R){
		Node P=null;
		if( N!=R ){
			P = R;
			while( P.left!=N && P.right!=N ){
				if((N.key).compareTo(P.key)<0){
					P = P.left;
				}
				else{
					P = P.right;
				}
			}
		}
		return P;
	}

	private Node findLeftmost(Node R){
		Node L = R;
		if( L!=null ) for( ; L.left!=null; L=L.left);
		return L;
	}

	private void deleteAll(Node N){
		N = null;
	}

	private String printInOrder(Node R){
		if( R!=null ){
			return printInOrder(R.left) + (R.key) + " " + (R.value) + "\n" + 
printInOrder(R.right);
		}
		return "";
	}

	private Node root;
	private int numPairs;

//Public Functions ---------------------------- 

	public Dictionary(){
    	root = null;
		numPairs = 0;
	}

	public boolean isEmpty(){
		return(numPairs==0);
	}

	public int size() {
      return numPairs;
  	}

	public String lookup(String k){
	   Node N;
	   N = findKey(root, k);
	   return ( N==null ? null : N.value );
	}

	public void insert(String k, String v) throws DuplicateKeyException{
		Node N, A, B;

		if( lookup(k)!=null ){
			throw new DuplicateKeyException("cannot insert duplicate keys");
		}

		N = new Node(k, v);
		B = null;
		A = root;
		while( A!=null ){
			B = A;
			if( k.compareTo(A.key)<0 ) A = A.left;
			else A = A.right;
		}
		if( B==null ) root = N;
		else if( k.compareTo(B.key)<0 ) B.left = N;
		else B.right = N;
		numPairs++;
	}

	public void delete(String k) throws KeyNotFoundException{

		if(lookup(k) == null){
      		throw new KeyNotFoundException("cannot delete non-existent key");
      	}

	   Node N, P, S;

	   N = findKey(root,k);

	   if( N.left==null && N.right==null ){  // case 1 (no children)
	      if( N==root ){
	         root = null;
	      }else{
	         P = findParent(N, root);
	         if( P.right==N ) P.right = null;
	         else P.left = null;
	      }
	   }else if( N.right==null ){  // case 2 (left but no right child)
	      if( N==root ){
	         root = N.left;
	      }else{
	         P = findParent(N, root);
	         if( P.right==N ) P.right = N.left;
	         else P.left = N.left;
	      }
	   }else if( N.left==null ){  // case 2 (right but no left child)
	      if( N==root ){
	         root = N.right;
	      }else{
	         P = findParent(N, root);
	         if( P.right==N ) P.right = N.right;
	         else P.left = N.right;
	      }
	   }else{                     // case3: (two children: N.left!=null && N.right!=null)
	      S = findLeftmost(N.right);
	      N.key = S.key;
	      N.value = S.value;
	      P = findParent(S, N);
	      if( P.right==S ) P.right = S.right;
	      else P.left = S.right;
	   }
	   numPairs--;
	}


	public void makeEmpty(){
      root = null;
      numPairs = 0;
   	}

	public String toString(){
		return printInOrder(root);
	}



}
