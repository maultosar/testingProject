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

public class MyObjects {

	public static void main(String args[]){
		
		//objects creations and calls of the methods they inherit or have personally
		
		Computer computerObject = new Computer("various", "various", "various", true, true);
		System.out.println(computerObject);
		computerObject.sendSignals();
		computerObject.userResponce();
		computerObject.returnResults();
		
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		
		PC pcObject = new PC("2.2 Ghz", "air", "monitor", false, false, "8 GB");
		System.out.println(pcObject);
		pcObject.sendSignals();
		pcObject.userResponce();
		pcObject.returnResults();
		pcObject.mantainingPeripherals();
		
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		
		Laptop laptopObject = new Laptop("1.9Ghz", "air", "screen", true, true, 4);
		System.out.println(laptopObject);
		laptopObject.sendSignals();
		laptopObject.userResponce();
		laptopObject.returnResults();
		laptopObject.closeOrOpen();
		
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		
		Mainframe mainframeObject = new Mainframe("4Ghz", "water cooling", "monitor", false, false, 25);
		System.out.println(mainframeObject);
		mainframeObject.sendSignals();
		mainframeObject.userResponce();
		mainframeObject.returnResults();
		mainframeObject.simulate();
		
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		
		Smartphone smartObject = new Smartphone("1.9Ghz", "natural", "screen", true, true, "touch");
		System.out.println(smartObject);
		smartObject.sendSignals();
		smartObject.userResponce();
		smartObject.returnResults();
		smartObject.call();
		
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		
		Human humanObject = new Human("various", "male or female",0, "various");
		System.out.println(humanObject);
		humanObject.eat();
		humanObject.sleep();
		humanObject.work();
		
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		
		Programmer torvalds =  new Programmer("Linus Torvalds", "male", 85, "programmer", "Linux");
		Programmer jobs =  new Programmer("Steve Jobs", "male", 79, "programmer", "MacOS");
		System.out.println(jobs);
		jobs.eat();
		jobs.sleep();
		jobs.work();
		jobs.program();
		
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		
		// as OS - Computer, OS - Human are compositions, the classes which inherit from them can be compositions as well. Here we used Programmer and PC, though they're not marked as composition in the UML diagram. 
		
		Linux linuxObject = new Linux("Linux", "UNIX", true, torvalds, pcObject,"Gnome");
		System.out.println(linuxObject);
		linuxObject.userInteraction();
		linuxObject.resourceAllocation();
		linuxObject.guiInteraction();
	}
	
}
