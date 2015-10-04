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
public class OS {

	//variables
	
	protected String name;
	protected String fileSystem;
	protected boolean openSourceCode;
	protected Programmer creator;
	protected PC installedOn;
	
	//constructor
	
	public OS(String name,String fileSystem,boolean openSourceCode,Programmer creator,PC installedOn){
		System.out.println("I'm OS base class");
		this.creator = creator;
		this.fileSystem = fileSystem;
		this.installedOn = installedOn;
		this.openSourceCode = openSourceCode;
		this.name = name;
		
	}
	
	//methods
	
	public void userInteraction(){
		
		System.out.println("OS interacts with the user.");
		
	}
	
	public void resourceAllocation(){
		
		System.out.println("OS allocates resouces of a computer.");
		
	}
	
	//toString method, which is returning the description of object 
	
	public String toString(){
		
		return "Operating system is called "+name+", file system "+fileSystem+(openSourceCode==true?", it has opened source code.\n\n": "it doesn't have opened source code.\n\n")+"Creator info:"+creator+"\n\n"+"Device instatted on:"+installedOn;
		
	}
}
