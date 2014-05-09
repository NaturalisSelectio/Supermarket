

/**Class Person creates an object that contains a person's name and their
 * rush and how fast they want to get out of the supermarket.
 * @author Victor
 *
 */
public class Person {

	//Stores the person's name
	private String name;	

	//Stores the person's level of impatience relative to other people
	private int hurry;
	
	//Estimated time of wait
	private int time;
	
	//Queue that a person has joined
	private int line;
	
	//Constructor: assigns the person a name and a random level of intolerance
	public Person(String name){
		this.name= name;
		this.hurry= (int) Math.ceil(10*Math.random());
	}	
	
	//Get's the person's name
	public String getName() {
		return name;
	}

	//Set the person's name
	public void setName(String name) {
		this.name = name;
	}
	
	//Get's the person's level of impatience
	public int getHurry() {
		return hurry;
	}

	//Set's the person level of impatience.
	public void setHurry(int hurry) {
		this.hurry = hurry;
	}
	
	//Prints out the person's name and level of impatience
	public String toString(){
		if (hurry<=4){
			return "" + name + " (who is not in a hurry)";
		}
		else if(hurry<=7){
			return "" + name + " (who doesn't want to spend too much time waiting)";
		}
		else{
			return "" + name + " (who will take the easiest way out at all costs)";
		}
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getTime() {
		return time;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getLine() {
		return line;
	}
}
