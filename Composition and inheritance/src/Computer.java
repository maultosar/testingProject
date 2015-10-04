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
public class Computer {

	//variables
	
	protected String cpuRate;
	protected String coolingType;
	protected String outputDevice;
	protected boolean battery;
	protected boolean portability;
	
	//constructor
	
	public Computer(String cpuRate,String coolingType,String outputDevice,boolean battery,boolean portability){
		
		this.cpuRate = cpuRate;
		this.coolingType = coolingType;
		this.outputDevice = outputDevice;
		this.battery = battery;
		this.portability = portability;
	}
	
	//methods
	
	public void sendSignals(){
		
		System.out.println("Computer sends a signal with a rate "+cpuRate+".");
		
	}
	
	public void userResponce(){
		
		System.out.println("Computer responses to a user.");
		
	}
	
	public void returnResults(){
		
		System.out.println("Computer returns results of calculations.");
		
	}
	
	//toString method, which is returning the description of object 
	
	public String toString(){
		
		return "Computer info: CPU rate is "+ cpuRate+", cooling type is "+ coolingType+ ", user gets output through "+outputDevice+(battery==true?", computer can have battery":", computer can't have battery")+ (portability==true?", the computer can be portable.":", the computer can not be portable.");
		
	}
}
