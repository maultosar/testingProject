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
public class Programmer extends Human {
	
	//variables
	
	protected String favouriteOs;
	
	//constructor
	
	public Programmer(String name,String sex,double weight,String occupation,String facouriteOS){
		
		super(name,sex,weight,occupation);
		this.favouriteOs=favouriteOs;
	}
	
	//overriden methods
	
	public void eat(){
		
		System.out.println("Programmer eats.");
		
	}
	public void sleep(){
		
		System.out.println("Programmer sleeps.");
		
	}
	public void work(){
	
		System.out.println("Programmer works.");
	
	}
	
	//additional method
	
	public void program(){
		
		System.out.println("The programmer programms in "+ favouriteOs+".");
		
	}
	
	//toString method, which is returning the description of object 
	
	public String toString(){
		return "Programmer's info: name is "+name+", "+sex+", weights "+weight+" kg"+", occupation is "+occupation+", favourite OS is "+favouriteOs+"." ;
	}
	
}
