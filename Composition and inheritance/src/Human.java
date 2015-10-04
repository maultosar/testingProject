/*
 * "Inheritance and composition program" program
 * 04.12.12
 * Vitaliy Sharandin 
 * nr171422
 * KrDzIa2011
 * 
 * Method of launching:
 * 1. From a console(in the directory with current file enter "javac NameOfProgram.java", press Enter and then write "java NameOfProgram" and the program will be executed)
 * 2. Just build and run the program in Jcreator. 
 * P.S. Run and build MyObjects.java file
 */
public class Human {

	//variables
	
	protected String name;
	protected String sex;
	protected double weight;
	protected String occupation;
	
	//constructor
	
	public Human(String name,String sex,double weight,String occupation){
		
		this.name = name;
		this.occupation = occupation;
		this.sex = sex;
		this.weight = weight;
		
	}
	
	//methods
	
	public void eat(){
		
		System.out.println("Human eats.");
		
	}
	public void sleep(){
		
		System.out.println("Human sleeps.");
		
	}
	public void work(){
	
		System.out.println("Human works.");
	
	}
	
	//toString method, which is returning the description of object 
	
	public String toString(){
		return "Human's info: name is "+name+", "+sex+", weights "+weight+" kg"+", occupation is "+occupation+".";
	}
	
}
