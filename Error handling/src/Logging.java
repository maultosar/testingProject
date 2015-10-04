public class Logging {
	
	static class NewException extends Exception{
		public NewException(String s){
			super(s);
		}
	}
	
public static void f() throws Exception {
	System.out.println("Inside f(),e.printStackTrace()");
	throw new NewException("thrown from f()");
}

public static void g() throws Throwable {
	try {
	f();
	} catch(NewException e) {
	System.out.println("Inside g(),e.printStackTrace()");
	e.printStackTrace(System.out);
	throw e;
	}
}

public static void main(String[] args) {
	try {
	g();
	} catch(Throwable e) {
	System.out.println("main: printStackTrace()");
	e.printStackTrace(System.out);
	}
	}
	}