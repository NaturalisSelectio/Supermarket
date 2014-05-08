public class Node {

	//Contains a object of the class person
	private Person data;
	
	//Contains the next node;
	private Node next;

	//Gets the person contained in the object
	public Person getData() {
		return data;
	}

	//Sets a person to be contained in data
	public void setData(Person data) {
		this.data = data;
	}

	//Gets the next node
	public Node getNext() {
		return next;
	}

	//Sets the next node
	public void setNext(Node next) {
		this.next = next;
	}
	
	//Constructor sets data to point to a person and leaves next null
	public Node(Person p){
		this.data= p;
		this.next= null;
	}
	
	//Constructor sets data to point to a person and stores an object of the class node in next
	public Node(Person p,Node n){
		this.data= p;
		this.next= n;
	}
	
	//Constructor sets data and next as null
	public Node(){
		this.data= null;
		this.next= null;
	}
	
	//Compares two nodes by comparing the person's name
	public boolean equals(Person compare){
		if (compare != null && this.data != null){
			if (this.data.getName().equals(compare.getName())){
				return true;			
			}
			else {
				return false;
			}
		}
		return false;

	}
}
