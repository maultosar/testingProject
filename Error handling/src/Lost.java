class VeryImportantException extends Exception {
public String toString() {return "A very important exception!";
}
}
class HoHumException extends Exception {
public String toString() {
return "A trivial exception";
}
}

class SelfishException extends Exception {
public String toString() {
return "Selfish exception";
}
}

public class Lost {
void f() throws VeryImportantException {
throw new VeryImportantException();
}

void dispose() throws HoHumException {
throw new HoHumException();
}

void disposeFromDispose() throws SelfishException{
	throw new SelfishException();
}

public static void main(String[] args) {
try {
	Lost lm = new Lost();
	
	try {
		lm.f();
	} 
	finally {
		lm.dispose();
	}
} catch(Exception e) {
System.out.println(e);
}
}
}