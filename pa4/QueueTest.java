//Josue Uriarte Reyes
//joauriar@ucsc.edu
//CS12M pa4
//03/07/19
public class QueueTest{


	public static void main(String[] args){

		Job job1 = new Job(1,3);
		Job job2 = new Job(4,5);
		Job job3 = new Job(7,8);

		Queue theQueue = new Queue();

		theQueue.enqueue(job1);
		theQueue.enqueue(job2);
		theQueue.enqueue(job3);
		System.out.println(theQueue.toString());
		System.out.println(theQueue.isEmpty());
		System.out.println(theQueue.length());

		System.out.println();

		theQueue.dequeue();
		System.out.println(theQueue.toString());
		System.out.println(theQueue.isEmpty());
		System.out.println(theQueue.length());
		System.out.println(theQueue.peek());

		System.out.println();

		theQueue.dequeueAll();
		System.out.println(theQueue.isEmpty());
		System.out.println(theQueue.length());

	}




}
