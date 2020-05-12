public class Dictionary implements DictionaryInterface{

   // private inner Node class
   private class Node {
      String key;
      String value;
      Node next;

      Node(String key, String value){
         this.key = key;
         this.value = value;
         next = null;
      }
   }

   // Fields for the IntegerList class
   private Node head;     // reference to first Node in List
   private int numItems;  // number of items in this List

   // IntegerList()
   // constructor for the Dictionary class
   public Dictionary(){
      head = null;
      numItems = 0;
   }


   // private helper function -------------------------------------------------

   // findKey()
   // returns a reference to the Node at position index in this Dictionary
   
   private Node findKey(String key){

      Node N = head;
      for(int i=1; i <= numItems; i++){
      	if(!(N.key.equals(key))){
        	N = N.next;
      	}
      }

    if(N == null){
    	return null;
    }

	return N;
    }


   // ADT operations ----------------------------------------------------------

   // isEmpty()
   // pre: none
   // post: returns true if this Dictionary is empty, false otherwise
   public boolean isEmpty(){
    	return(numItems == 0);
   }

   // size()
   // pre: none
   // post: returns the number of entries in this Dictionary
   public int size() {
      return numItems;
   }

   // lookup()
   // pre: none
   // post: returns value associated key, or null reference if no such key exists
   public String lookup(String key){

      Node N = findKey(key);

      if(N == null){
      	return null;
      }

      return N.value;

   }

   // Insert()
   // inserts newItem into Dictionary
   // pre: 
   // post: lookup(key)!=null
   public void insert(String key, String value) 
		throws DuplicateKeyException{

			if(lookup(key) != null){
				throw new DuplicateKeyException("cannot insert duplicate keys");
			}

			if(head == null){
				Node N = new Node(key,value);
				N.next = head;
				head = N;
			}
			else{

				Node X = head;
				Node Y = head;
				for( ; X!=null; X=X.next){
					if(X.key.equals(key)){
						break;
					}

					else{
						Y = X; 
					}
					
				}
				
				Node P = findKey(Y.key);
				Node C = P.next;
				P.next = new Node(key,value);
				P = P.next;
				P.next = C;
			}
			numItems++;
   		}

   // delete()
   // deletes pair with the given key
   // pre: lookup(key) == null
   public void delete(String key)
      throws KeyNotFoundException{

      	if(lookup(key) == null){
      		throw new KeyNotFoundException("cannot delete non-existent key");
      	}

		    if(findKey(key) == head){
		      Node N = head;
		      head = head.next;
		      N = null;
		    }
        else{
		      Node X = head;
		      Node Y = head;
		      for( ; X!=null; X=X.next){
				    if(X.key.equals(key)){
				      break;
				    }

				    else{
				      Y = X; 
				    }
				
		      }
			
          Node P = findKey(Y.key);
          Node N = P.next;
          P.next = N.next;
          N.next = null;
        }
    	numItems--;
      }

   // makeEmpty()
   // pre: none
   // post: isEmpty()
   public void makeEmpty(){
      head = null;
      numItems = 0;
   }

   // toString()
   // pre: none
   // post:  prints current state to stdout
   // Overrides Object's toString() method
   public String toString(){
      StringBuffer sb = new StringBuffer();
      Node N = head;

      if(numItems == 0){
        return "";
      }

      for( ; N!=null; N=N.next){
         sb.append(N.key).append(" ").append(N.value);
         if(N!=null){
            sb.append("\n");
         }
      }
      return new String(sb);
   }


}

