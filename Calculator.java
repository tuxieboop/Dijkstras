import java.util.Scanner;
/**
* Evaluates a mathematical expression consisting of integers and the operations +, -, *, /, and ^.
*/
public class Calculator{
	/**
	* Constructor for a Calculator.
	*/
	public Calculator(){
	}
	
	/**
	* Evaluates a mathematical expression.
	* @param input the mathematical expression
	* @return the result
	*/
	public static double evaluate(String input){
		input = formatEquation(input); // formats the input to be read by a scanner
		Stack vals = new Stack<Double>(); // for numbers
		Stack ops = new Stack<String>(); // for operations
		Scanner sc = new Scanner(input); // for reading input
		
		while(sc.hasNext()){
			String s = sc.next();
			
			// left parenthesis: do nothing
			if(s.equals("(")){
			}
			// pushes operations to Stack ops
			else if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^")){
				ops.push(s);
			}
			// right parentheses: do the latest operation to the latest two numbers
			else if(s.equals(")")){
				String op = ops.pop().toString();
				double v2 = Double.parseDouble(vals.pop().toString()); // to avoid the Object can't become a Double thing...
				double v1 = Double.parseDouble(vals.pop().toString());
				
				if(op.equals("+"))
					vals.push(v1 + v2);
				else if (op.equals("-"))
					vals.push(v1 - v2);
				else if (op.equals("*"))
					vals.push(v1 * v2);
				else if (op.equals("/"))
					vals.push(v1 / v2);
				else if (op.equals("^"))
					vals.push(Math.pow(v1, v2));
			}
			// pushes numbers to Stack vals
			else{
				vals.push(Double.valueOf(s));
			}
		}
		return Double.valueOf(vals.pop().toString());
	}
	
	/**
	* Formats an equation so that a scanner can read it (numbers/operations/parentheses are each separated by a space) in evaluate(). Important: separates numbers (including multi-digit ones) from other things.
	* @param input the raw equation
	* @return the formatted equation.
	*/
	private static String formatEquation(String input){
		String result = "";
		
		for(int i = 0; i < input.length(); i++){
			char curr = input.charAt(i);
			int ascii = (int)curr;
			
			int temp = i;
			// if the current char is a number, get the rest of the multi-digit number before putting a space.
			if(ascii < 58 && ascii > 47){
				while(ascii < 58 && ascii > 47){
					result += curr;
					temp += 1;
					curr = input.charAt(temp);
					ascii = (int)curr;
				}
				i = temp - 1;
				result += " ";
			}
			// if it's not a number, add it and put a space.
			else if(curr != ' ')
				result += curr + " ";
		}
		return result;
	}
	
	/**
	* Takes an inputted equation and evaluates it using Calculator.
	*/
	public static void main(String[] args){
		System.out.println("\nPlease enter your equation.\n");
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine(); // ((((90/30)-17)+((4*6)/2))+(8^2)) <-62
		Calculator c = new Calculator();
		System.out.println(c.evaluate(input));
	}
}
