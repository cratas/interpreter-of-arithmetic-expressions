import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class MainClass {

	private static final String[] OPERATORS = new String[4];
	static {
		OPERATORS[0] = "+";		
		OPERATORS[1] = "-";		
		OPERATORS[2] = "*";		
		OPERATORS[3] = "/";		
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
	
	private static boolean isNumber(char token) {
		return (token >= '0' && token <= '9') ? true : false;
	}
	
	private static boolean isNumber(String exp) {
		return exp.matches("\\d+");
	}
	
	private static boolean isOperator(char token) {
		return Arrays.asList(OPERATORS).contains(Character.toString(token));
	}
	
	public static boolean hasPrecedence(char op1, char op2)
	{
		if (op2 == '(' || op2 == ')')
			return false;
		if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
			return false;
		else
			return true;
	}
	
    public static int calculate(char operator, int b, int a)
	{
		switch (operator)
		{
			case '+':
				return a + b;
			case '-':
				return a - b;
			case '*':
				return a * b;
			case '/':
				if (b == 0) 
					throw new UnsupportedOperationException("You can not divide by zero ..");
				return a / b;
		}
		return 0;
	}
	
	private static int handleExpression(String exp) {
		//checking format of expression
		if(!bracketsCheck(exp))
			return -1;
		
		//if exp is number we can return it
		if(isNumber(exp))
				return Integer.parseInt(exp);
		
		
		char[] tokens = exp.replaceAll(" ", "").toCharArray();
		
		Stack<Integer> numbers = new Stack<Integer>();
		Stack<Character> operators = new Stack<Character>();
		
		
		
		for(int i = 0; i < tokens.length; i++ ) {

			//check format of expression, there cannot be 2 operators successive
			if(i > 0 && isOperator(tokens[i - 1]) && isOperator(tokens[i])) {
				return -1;
			}
			
			//if token is number or numbers sequence, add it into numbers stack
			if(isNumber(tokens[i])) {
				StringBuffer sBuffer = new StringBuffer();
				
				//handle more numbers than 1
				while(i < tokens.length && isNumber(tokens[i]))
					sBuffer.append(tokens[i++]);
								
				numbers.push(Integer.parseInt(sBuffer.toString()));
				i--;
			} 
			else if(tokens[i] == '(') {
				operators.push(tokens[i]);
			} 
			else if(tokens[i] == ')') {
				while(operators.peek() != '(') {
					numbers.push(calculate(operators.pop(), numbers.pop(), numbers.pop()));
				}
				
				operators.pop();
			} 
			else if(isOperator(tokens[i])) {
				while(!operators.empty() && hasPrecedence(tokens[i], operators.peek())) {
					numbers.push(calculate(operators.pop(), numbers.pop(), numbers.pop()));	
				}
				operators.push(tokens[i]);
			}
		}

		
		while(!operators.empty()) {
			numbers.push(calculate(operators.pop(), numbers.pop(), numbers.pop()));
		}
		
		return numbers.pop();
	}
}

