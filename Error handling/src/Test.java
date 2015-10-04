class ParentException extends Exception{
	
}

class ParentTest{
	
	public ParentTest() throws ParentException{
		throw new ParentException();
	}
	
}

public class Test extends ParentTest{
	
	public Test() throws ParentException{
		super();
	}
	
	
	public static void main(String[] args) {
		try{
			Test t = new Test();
		}
		catch(ParentException e){
			System.out.println(e);
		}
		
	}
}
