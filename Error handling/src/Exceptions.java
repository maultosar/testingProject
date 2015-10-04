class SimpleException extends ArrayIndexOutOfBoundsException {
	SimpleException(String s){super(s);}
}

public class Exceptions {
	public void f() throws SimpleException {
	System.out.println("Throw SimpleException from f()");
	throw new SimpleException("Thrown from f()");
}

public static void main(String[] args) {
	
	String[] arr = {"1","2"};
	
	try {
		
		System.out.println(arr[2]);
		
	} catch(SimpleException e) {
		e.printStackTrace(System.out);
	}
	
}
}