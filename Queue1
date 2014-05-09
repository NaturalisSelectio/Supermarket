

public class Queue1 {

	// Indicates the first person in the queue
	private Node first;

	// Indicates how fast the people in a certain queue leave
	private int speed;

	// Indicates the last person in the queue
	private Node last;

	// Length of line
	private int length;	

	// number of the current queue
	private int queueNum;

	// Constructor for queue1 that initializes the queue
	public Queue1() {
		this.first = new Node(new Person(null));
		this.length = 0;
		this.last = first;
		this.setSpeed();
	}

	// Adds a person to the end of the line
	public void joinQ(Person p) {
		if (isEmpty()) {
			first.setData(p);
			this.last = first;
			p.setTime(this.getTime());
			p.setLine(queueNum);
			length++;
		} else {
			last.setNext(new Node(p));
			this.last = last.getNext();
			p.setTime(this.getTime());
			p.setLine(queueNum);
			length++;
		}
	}

	// Determines if the line is empty
	public boolean isEmpty() {
		if (length == 0)
			return true;
		else
			return false;
	}

	// Provides the speed at which costumers go through an specific queue
	public int getSpeed() {
		return speed;
	}

	// Changes the speed of a queue to some random speed
	public void setSpeed() {
		speed = (int) Math.ceil(10 * Math.random());
	}
	
	// Changes the speed of a queue to some random speed
	public void setSpeed(int n) {
		this.speed = n;
	}
	

	// Returns the length of the queue
	public int getLength() {
		return length;
	}

	// Sets the length of the queue
	public void setLength(int length) {
		this.length = length;
	}

	// Notifies the user when a person leaves the queue
	public String toStringLeave() {
		return first.getData().getName() + " has left Queue " + queueNum + ".\n";
	}

	// Notifies the user when a person joins the queue
	public String toStringJoin() {
		return last.getData().getName() + " has joined Queue " + queueNum + ".\n";
	}
	
	//Takes the time it takes the head of a line to leave the queue and 
	//multiplies it times the length in order to get total time needed to leave.
	public int getTime(){
		return speed*length + speed;
	}

	// set the first person
	public void setFirst(Node first) {
		this.first = first;
	}

	// get the first person
	public Node getFirst() {
		return first;
	}

	
	//First costumer in line leaves 
	public Person leave() {
		//System.out.println(toStringLeave());
		Person person= first.getData();
		if (length==1){
			this.first = new Node(null);
		}
		else{
			this.first = first.getNext();
		}		
		length--;
		return person;
	}
		
	/**
	 * @return the last
	 */
	public Node getLast() {
		return last;
	}

	//Removes a costumer from the line
	public Node remove(Person skip) {
		
		Node before = this.first;
		Node after = new Node(null);
		Node temp = new Node(null);
		
		//If the costumer is the first person on the line
		if (before.equals(skip)){
			length--;
			if (length==0){
				this.first= new Node(null);
			}
			else{
				this.first= first.getNext();
				first.getData().setTime(getTime());
			}	
			return before;
		}
		//If the costumer is the last person on the line
		else if (last.equals(skip)){
			length--;
			temp= first;
			for (int n=1; n< length; n++){
				temp= temp.getNext();
			}
			last= temp;
			temp= last.getNext();
			last.setNext(new Node(null));
			return temp;
		}
		//If the person is between the first and last person, the method will return the person
		//otherwise it will say that the person is not in the line
		else{
			temp= first;
			for (int n=2; n< length; n++){
			temp= temp.getNext();
			after= temp.getNext();
			if (temp.equals(skip)){
				before.setNext(after);
				length--;
				after.getData().setTime(this.getTime());
				for (int g=n; g< length; g++){
					after= after.getNext();
					after.getData().setTime(this.getTime());
				}
				return temp;
			}
			else {
				before= temp;				
			}
			}
			System.out.println("The person described is not in the line.");
			return last.getNext();
		}
	}
	
	// Return the queue number
	public int getQueueNum() {
		return queueNum;
	}

	// set the queue number
	public void setQueueNum(int queueNum) {
		this.queueNum = queueNum;
	}
}
