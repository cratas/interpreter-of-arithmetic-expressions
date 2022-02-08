import java.util.Scanner;
import java.util.Vector;

public class MainClass {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Vector<String> lines = new Vector<String>();
		
		int countOfLines = scanner.nextInt();
		scanner.nextLine();
		
		while(countOfLines != 0) {
			System.out.println("Enter expression:");
			lines.add(scanner.nextLine());
			--countOfLines;
		}
		
		
		System.out.println("size of vector: " + lines.size());
		
		for(String x : lines) {
			int result = countExpression(x);
			System.out.println(result == -1 ? "ERROR" : result);
		}
		
		scanner.close();
	}
	
	private static int countExpression(String expression) {
		
		
		return -1;
	}

}
