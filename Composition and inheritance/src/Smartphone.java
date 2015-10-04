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
public class Smartphone extends Computer{

	//variables
	
	protected String screenType;
	
	//constructor
	
	public Smartphone(String cpuRate,String coolingType,String outputDevice,boolean battery,boolean portability,String screenType){
		
		super(cpuRate,coolingType,outputDevice,battery,portability);
		this.screenType = screenType;
	}

	//overriden methods
	
	public void sendSignals(){
		
		System.out.println("Smartphone sends a signal with a rate "+cpuRate+".");
		
	}
	
	public void userResponce(){
		
		System.out.println("Smartphone responses to a user.");
		
	}
	
	public void returnResults(){
		
		System.out.println("Smartphone returns results of calculations.");
		
	}
	
	//additional method
	
	public void call(){
		
		System.out.println("Smartphone can call.");
		
	}
	
	//toString method, which is returning the description of object 
	
	public String toString(){
		
		return "Smartphone parameters: CPU rate is "+ cpuRate+", cooling type is "+ coolingType+ ", user gets output through "+screenType+" "+outputDevice+(battery==true?", there is battery in smartphone":", there is no battery in smartphone")+ (portability==true?", the smartphone is portable.":", the smartphone is not portable.");
		
	}
}
