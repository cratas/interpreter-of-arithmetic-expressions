import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class MainClass {

	private static final Map<String, Integer>OPERATORS = new HashMap<String, Integer>();
	
	static {
		OPERATORS.put("+", 1);		
		OPERATORS.put("-", 1);
		OPERATORS.put("*", 2);
		OPERATORS.put("/", 2);
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); 	//Scanner for scan lines from console
		Vector<String> lines = new Vector<String>();	//vector for saving loaded lines
		
		//getting count of lines to scan
		int countOfLines = scanner.nextInt();
		scanner.nextLine();
		
		//scanning lines
		while(countOfLines != 0) {
			System.out.println("Enter expression:");
			lines.add(scanner.nextLine());
			--countOfLines;
		}
		
		//counting expression on every line
		for(String line : lines) {
			int result = handleExpression(line);
			System.out.println(result == -1 ? "ERROR" : result);
		}
		
		scanner.close();
	}
	
	private static boolean bracketsCheck(String exp) {
		if(exp.chars().filter(ch -> ch == '(').count() == exp.chars().filter(ch -> ch == ')').count()) {
			return bracketsOrderCheck(exp);
		}
		return false;
	}
	
	private static boolean bracketsOrderCheck(String exp) {
		Stack<Character> brackets = new Stack<Character>();
		
		for(char c : exp.toCharArray()) {
			if(c == '(') {
				brackets.push(c);				
			}
			if(c == ')' ) {
				if(brackets.size() != 0) {
					brackets.pop();					
				}
			}
		}
		
		return brackets.size() == 0 ? true : false;
	}
	
	public static boolean isNumber(String token) {
		return token.matches("\\d+");
	}
	
	public static boolean isOperator(String token) {
		return OPERATORS.containsKey(token);
	}
	
	private static int handleExpression(String exp) {
		//checking format of expression
		if(!bracketsCheck(exp))
			return -1;
		
		//if exp is number we can return it
		if(isNumber(exp))
				return Integer.parseInt(exp);
		
		Stack<Integer> numbers = new Stack<Integer>();
		
		for(int i = 0; i < exp.length(); i++) {
			String token = Character.toString(exp.charAt(i));	//want to work with string rather than character
			
			if(isOperator(token)) {

			}
			
			if(isNumber(token)) {
				numbers.push(Integer.parseInt(token));
			}
			
			if(token.equals("(")) {
				
			}
		}
		
		
		return 1000;
	}
}

