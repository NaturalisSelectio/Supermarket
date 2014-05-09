

import java.util.ArrayList;
import java.util.Random;

public class Market{

	// Number of customers left in the store
	private static int remaining;

	// Queues left open
	private static int open;

	public static boolean question = true;

	private static ArrayList<Person> people = new ArrayList<Person>();

	// List of people in a hurry
	private static ArrayList<Person> impatient = new ArrayList<Person>();

	// Contains all the speeds
	private static ArrayList<Queue1> lines = new ArrayList<Queue1>();

	// Synchronize lock objects
	public static Object lockObject = new Object();
	public static Object lockObject1 = new Object();
	public static Object lockObject2 = new Object();
	public static Object lockObject3 = new Object();
	public static Object lockObject4 = new Object();
	public static Object lockObject5 = new Object();
	public static Object lockObject6 = new Object();
	public static Object lockObject7= new Object();
	public static Object lockObject8 = new Object();
	public static Object lockObject9 = new Object();
	
	public static String info ="";

	public Market(ArrayList<Queue1> lines) {

		System.out.println("The Supermarket has opened!\n");
		// Number of people remaining
		remaining = 0;
		// Number of queues left open
		open = 4;

		this.lines = lines;
		
		this.initialize();
				
		// Start having people join the queues
		joinPeople(this);
		
		// Run the queues
		runQueues(0, lockObject5);
		runQueues(1, lockObject6);
		runQueues(2, lockObject7);
		runQueues(3, lockObject8);
		runQueues(4, lockObject9);

	}

	public static void initialize() {
		// add miscellaneous people
        // Number of people HERERERERERERERERERERERERE!!!!!!!
		for (int i = 0; i < 50; i++) {
			remaining++;
			people.add(new Person("Customer: " + i));
		}
		System.out.println();
	}

	public static String getInfo(){
		return info;
	}
	
	// Adds a person to the queue that they have decided on
	public void join(Person person) {
		int n = decide(person);
		/*
		System.out.println(person.getName() + " has decided to join Queue "
				+ (n + 1) + ".\n");
		*/
		info = (person.getName() + " has decided to join Queue "
				+ (n + 1) + ".\n");
		lines.get(n).joinQ(person);
		/*
		synchronized (lockObject1) {
			printQueue(lines.get(n));
		}
		*/
		// puts people in a hurry in an ArrayList
		if (person.getHurry() > 7) {
			impatient.add(person);
		}
	}
	
	/*
	// Print the list of people in a queue
	public static void printQueue(Queue1 q) {
		Node head = q.getFirst();
		System.out.println("|| Queue " + q.getQueueNum() + " ||");
		for (Node n = head; n != null; n = n.getNext()) {
			if (n.getData() != null) {
				System.out.println(n.getData().getName() + " ");
			}
		}
		System.out.println("-----BACK-----\n");
	}
	*/

	// Algorithm that determines which line a person will be joining
	public static int decide(Person p) {
		int fastest = 0;
		int second = 0;
		int third = 0;
		// Random number generator
		Random g = new Random();

		// Get the fastest time to get out of a line, the second fastest and the
		// third fastest
		for (int i = 0; i < lines.size(); i++) {
			if (lines.get(i).getTime() < lines.get(fastest).getTime()) {
				third = second;
				second = fastest;
				fastest = i;
			} else if (lines.get(i).getTime() <= lines.get(second).getTime()
					|| second == fastest) {
				third = second;
				second = i;
			} else if (lines.get(i).getTime() <= lines.get(third).getTime()
					|| third == fastest || second == third) {
				third = i;
			}
		}

		// In the greatest hurry
		if (p.getHurry() > 7) {
			// Return queue with fastest speed
			return fastest;
		}
		// In somewhat of a hurry
		else if (p.getHurry() > 4 && p.getHurry() <= 7) {
			return (Math.random() <= .5 ? second : third);
		}
		// Not in a hurry
		else {
			// Returns a random queue to join
			return (Math.random() <= .3 ? third : g.nextInt(lines.size()));
		}
	}

	// Runs the thread having people join queues

	// Runs the thread having people join queues
	public static void joinPeople(final Market cornell){	
		// Start the join thread
		new Thread(){
			public void run(){
				for (int i = 0; i < people.size(); i++){
					synchronized(lockObject){
						try{
							// People join a queue every second
							cornell.join(people.get(i));
							Thread.sleep(2000);
						}
						catch (InterruptedException e){
							e.printStackTrace();
						}
					}
				}
				// There are no more people fed into the queues
				question = false;
			}
		}.start();
	}
	
	// Runs the queues (changing speeds)
	public static void runQueues(final int n, final Object a) {
		new Thread() {
			public void run() {
				Queue1 line = lines.get(n);
				while (!(question == false && remaining==0)) {
					synchronized (a) {
						// If the line is not empty
						if(!(line.isEmpty())){
							//If the first person in the line is not an empty node
							if(line.getFirst().getData()!= null){
								try {	
									int speed = lines.get(n).getSpeed();
									// Randomly changes queue's customer processing speed
									if (Math.random() < .2) {
										// set a new speed
										line.setSpeed();
										int speed2 = line.getSpeed();
										
										// if the new speed is faster than the old one
										if (speed2 > speed){
											/*
											System.out.println("The cashier at Queue " 
													+ line.getQueueNum() + " has sped up!\n");		
													*/
											info = ("The cashier at Queue " 
													+ line.getQueueNum() + " has sped up!\n");
											
										}
										// if the new speed is slower than the old one or if its the same
										else if (speed2 <= speed){
											/*
											System.out.println("The cashier at Queue " 
													+ line.getQueueNum() + " has slowed down...\n");
													*/
											info = ("The cashier at Queue " 
													+ line.getQueueNum() + " has slowed down...\n");
											decideSkip(line);
										}			
										// Sees if any customers want to change lines
										
										Thread.sleep(2500);									
									} else {
										// Processes next person
										
											Thread.sleep(speed * 2500);
											leave(n);				
									}
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}						
						}						
					}
				}				
				open--;				
			}			
		}.start();
	}
	
	// Algorithm that determines if a person decides to skip and to which line
	// they would skip.
	public static void decideSkip(Queue1 line) {
		for (int a = 0; a < 5; a++) {			
			if ((lines.get(a).getLast().getData().getTime() > line.getTime() || line.isEmpty()) && lines.get(a).getLast().getData().getHurry()>7) {
				// add to number of people who switch lines
				
				// get the person who is changing lines
				Person moving = lines.get(a).getLast().getData();
				
				Queue1 temp = lines.get(moving.getLine()-1);
				temp.remove(moving);
				// change lines
				line.joinQ(moving);
				/*
				System.out.println(moving.getName()
						+ " has decided to switch to Queue "
						+ (line.getQueueNum()) + ".\n");		
				*/
				info = 	(moving.getName()
						+ " has decided to switch to Queue "
						+ (line.getQueueNum()) + ".\n");	
				
				/*
				// add to average departure time of people who switch lines				
				synchronized(lockObject8){
					printQueue(line);
				}
				*/
			}
			
		}
	}
	
	//Deletes a person from a given queue and from the list of impatient people
	public static Person leave(int n) {	
		// Get the person leaving at the front of the line
		Person person= null;
		if (lines.get(n).getFirst().getData() != null){
			person= lines.get(n).leave();
			if (person.getHurry() > 7) {
				impatient.remove(person);
			}
			info = person.getName() + " has left the store.\n";
		}
		// If the person was in a hurry, remove them from the list of people in a hurry
		
		//Removes a person from remaining
		remaining--;
		/*
		synchronized (lockObject3) {
			if (lines.get(n).getFirst().getData() != null){
				printQueue(lines.get(n));
			}
			else{
				System.out.println("|| Queue " + lines.get(n).getQueueNum() + " ||" +
						"\n----EMPTY----\n");
			}
		}
		*/
		return person;
	}
	
}
