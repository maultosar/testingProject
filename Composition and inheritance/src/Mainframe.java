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
public class Mainframe extends Computer{

	//variables
	
	protected int cpuNumber;
	
	//constructor
	
	public Mainframe(String cpuRate,String coolingType,String outputDevice,boolean battery,boolean portability,int cpuNumber){
		
		super(cpuRate,coolingType,outputDevice,battery,portability);
		this.cpuNumber = cpuNumber;
	}

	//overriden methods
	
public void sendSignals(){
	
	System.out.println("Mainframe sends a signal with a rate "+cpuRate+".");
	
}

public void userResponce(){
	
	System.out.println("Mainframe responses to a user.");
	
}

public void returnResults(){
	
	System.out.println("Mainframe returns results of calculations.");
	
}

//additional method

	public void simulate(){
		
		System.out.println("Mainframe is simulating the Big Bang due to the power of "+cpuNumber+" CPUs.");
		
	}
	
	//toString method, which is returning the description of object 
	
	public String toString(){
		
		return "Mainframe parameters: CPU rate is "+ cpuRate+", there are "+cpuNumber+" CPUs "+", cooling type is "+ coolingType+ ", user gets output through "+outputDevice+(battery==true?", there is battery in mainframe":", there is no battery in mainframe")+ (portability==true?", the mainframe is portable.":", the mainframe is not portable.");
		
	}

}
