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
public class PC extends Computer {

	//variables
	
	protected String ram ;
	
	//constructor
	
	public PC(String cpuRate,String coolingType,String outputDevice,boolean battery,boolean portability, String ram){
		
		super(cpuRate,coolingType,outputDevice,battery,portability);
		this.ram = ram;
	}
	
	//overriden methods
	
	public void sendSignals(){
		
		System.out.println("PC sends a signal with a rate "+cpuRate+".");
		
	}
	
	public void userResponce(){
		
		System.out.println("PC responses to a user.");
		
	}
	
	public void returnResults(){
		
		System.out.println("PC returns results of calculations.");
		
	}
	
	//additional method
	
	public void mantainingPeripherals(){
		
		System.out.println("PC is able to maintain peripherals.");
		
	}
	
	//toString method, which is returning the description of object 
	
	public String toString(){
		
		return "PC parameters: CPU rate is "+ cpuRate+", RAM is "+ram+", cooling type is "+ coolingType+ ", user gets output through "+outputDevice+(battery==true?", there is battery in PC":", there is no battery in PC")+ (portability==true?", the PC is portable.":", the PC is not portable.");
		
	}
}
