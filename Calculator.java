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
		input = formatEquation(input);
		Stack vals = new Stack<Double>();
		Stack ops = new Stack<String>();
		Scanner sc = new Scanner(input);
		
		while(sc.hasNext()){
			
			String s = sc.next();
			
			if(s.equals("(")){
			}
			else if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^")){
				ops.push(s);
			}
			else if(s.equals(")")){
				String op = ops.pop().toString();
				double v2 = Double.parseDouble(vals.pop().toString());
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
			else{
				vals.push(s);
			}
		}
		return Double.parseDouble(vals.pop().toString());
	}
	
	/**
	* Formats an equation so that a scanner can read it (numbers/operations/parentheses are each separated by a space) in evaluate().
	* @param input the raw equation
	* @return the formatted equation.
	*/
	private static String formatEquation(String input){
		String result = "";
		
		for(int i = 0; i < input.length(); i++){
			
			char curr = input.charAt(i);
			int ascii = (int)curr;
			
			int temp = i;
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
