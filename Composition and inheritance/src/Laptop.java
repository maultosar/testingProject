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
public class Laptop extends Computer{

	//variables
	
	protected int usbNumber;
	
	//constructor
	
	public Laptop(String cpuRate,String coolingType,String outputDevice,boolean battery,boolean portability,int usbNumber){
		
		super(cpuRate,coolingType,outputDevice,battery,portability);
		this.usbNumber= usbNumber;
	}

	//overriden methods
	
	public void sendSignals(){
		
		System.out.println("Laptop sends a signal with a rate "+cpuRate+".");
		
	}
	
	public void userResponce(){
		
		System.out.println("Laptop responses to a user.");
		
	}
	
	public void returnResults(){
		
		System.out.println("Laptop returns results of calculations.");
		
	}
	
	//additional method
	
	public void closeOrOpen(){
		
		System.out.println("Laptop is able to close and open.");
		
	}
	
	//toString method, which is returning the description of object 
	
	public String toString(){
		
		return "Laptop parameters: CPU rate is "+ cpuRate+", cooling type is "+ coolingType+ ", user gets output through "+outputDevice+", there are "+usbNumber+" USBs"+(battery==true?", there is battery in laptop":", there is no battery in laptop")+ (portability==true?", the laptop is portable.":", the laptop is not portable.");
		
	}
	
}
