import java.util.Scanner;

public class Runtime {
	
	static int[] arr = {1,2,3,4,5};
	
	static void getElement(int i) {
		if(i>5 || i<0)
			throw new RuntimeException("From getElement()");
		
		System.out.println(arr[i-1]);
		
	}

	public static void main(String[] args) {
		int x =1;
		while(x>0){
		Scanner s = new Scanner(System.in);
		
		System.out.print("Enter array index you want to access: ");
		int input = s.nextInt();
		
		getElement(input);
		}
		
	}
}