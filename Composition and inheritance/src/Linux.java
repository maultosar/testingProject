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
public class Linux extends OS {

	//variables
	
	protected String guiName;
	
	//constructor
	
	public Linux(String name,String fileSystem,boolean openSourceCode,Programmer creator,PC installedOn,String guiName){
		
		super(name,fileSystem,openSourceCode,creator,installedOn);
		this.guiName = guiName;
	}
	
	//additional method
	
	public void guiInteraction(){
		
		System.out.println("Linux interacts with user due to "+guiName+" GUI.");
		
	}
	
	//overriden methods
	
	public void userInteraction(){
		
		System.out.println("Linux interacts with the user.");
		
	}
	
	public void resourceAllocation(){
		
		System.out.println("Linux allocates resouces of a computer.");
	}
	
	//toString method, which is returning the description of object 
	
	public String toString(){
		
		return "Operating system is called "+name+", file system "+fileSystem+(openSourceCode==true?", it has opened source code.\n\n": "it doesn't have opened source code.\n\n")+"Creator of this OS:\n"+creator+"\n\n"+"OS is installed on:\n"+installedOn+"\n";
		
	}
}
